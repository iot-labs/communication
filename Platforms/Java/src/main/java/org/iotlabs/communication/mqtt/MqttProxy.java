package org.iotlabs.communication.mqtt;

import com.google.gson.stream.JsonReader;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.iotlabs.communication.mqtt.brokers.SimpleMqttBroker;
import org.iotlabs.communication.mqtt.clients.callback.LogMqttCallback;
import org.iotlabs.communication.mqtt.clients.publisher.PublisherPool;
import org.iotlabs.communication.mqtt.clients.receiver.ReceiverAsync;
import org.iotlabs.models.BaseModel;
import org.iotlabs.models.mqtt.recievers.Receiver;
import org.iotlabs.models.mqtt.recievers.Receivers;
import org.iotlabs.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MqttProxy {
    private static final Logger logger = Logger.getLogger(MqttProxy.class);

    private static class Holder {
        private static final MqttProxy MQTT_PROXY = new MqttProxy();
    }

    public static MqttProxy getInstance() {
        return Holder.MQTT_PROXY;
    }

    /**
     * map which store mqtt receivers.
     * key : client id
     */
    private Map<String, ReceiverAsync> receiverMap;

    /**
     * pool contains Publishers
     */
    private PublisherPool publisherPool;

    private SimpleMqttBroker simpleMqttBroker;

    private MqttProxy() {
        receiverMap = new ConcurrentHashMap<>();
    }

    public synchronized void startMqttBroker(String configPath) {
        if (simpleMqttBroker == null) {
            simpleMqttBroker = new SimpleMqttBroker(configPath);
        }
        simpleMqttBroker.start();
    }

    public synchronized void stopMqttBroker() {
        simpleMqttBroker.stop();
    }

    /**
     */
    /**
     * register mqtt receivers.
     * @param receivers wrapped list of {@link Receiver}
     * @param mqttCallback moquette callback. If callback is null, automatically use {@link LogMqttCallback}
     * @param shouldClearConnection If true and receiver connected, force disconnect.
     */
    public void registerReceivers(Receivers receivers, MqttCallback mqttCallback, boolean shouldClearConnection) {
        if (shouldClearConnection) {
            receiverMap.forEach((clientId, receiverAsync) -> receiverAsync.unsubscribe());
            receiverMap.clear();
        }
        for(Receiver receiver : receivers.getReceiverList()) {
            try {
                ReceiverAsync receiverAsync = new ReceiverAsync(receiver);
                if (mqttCallback == null) {
                    mqttCallback = new LogMqttCallback();
                }
                receiverAsync.subscribe(mqttCallback);
                receiverMap.put(receiver.getClientId(), receiverAsync);
            } catch (MqttException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * get receivers from file
     * @param receiverFilePath file path
     * @return {@link Receivers}
     */
    public Receivers getReceiversFromFile(String receiverFilePath) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(
                    new InputStreamReader(new FileInputStream(receiverFilePath), Charset.forName("UTF-8"))
            );
            Receivers receivers = BaseModel.fromJsonString(reader, Receivers.class);
            logger.info("Receivers in " + receiverFilePath + " registered.");
            return receivers;
        } catch (FileNotFoundException e) {
            logger.error("Receiver file " + receiverFilePath + " not found.", e);
        } finally {
            IOUtils.closeQuite(reader);
        }
        return null;
    }

    /**
     * un-subscribe receiver
     * @param clientId receiver client id to unsubscribe
     */
    public synchronized void unsubscribeReceiver(String clientId) {
        ReceiverAsync receiverAsync = receiverMap.get(clientId);
        if (receiverAsync != null) {
            receiverAsync.unsubscribe();
        }
        receiverMap.remove(clientId);
    }

    /**
     * initialize publisher client pool
     * @param poolSize pools item size
     * @param brokerUrl mqtt brokre url to send payload
     * @param isSync should create sync client
     * @param force should reset all client.
     *              If true, call `clearPublisherPool` and make exist pool to null.
     */
    public synchronized void initPublisherPool(int poolSize, String brokerUrl, boolean isSync, boolean force) {
        if (force
                && publisherPool != null) {
            clearPublisherPool();
            publisherPool = null;
        }

        if (publisherPool == null) {
            publisherPool = new PublisherPool(poolSize, brokerUrl, isSync);
        }
    }

    /**
     * publish message to mqtt broker
     * @param topic message topic
     * @param payload message payload
     * @param qos qos
     * @param isRetained retain flag
     */
    public void publish(String topic, String payload, int qos, boolean isRetained) {
        if (publisherPool == null) {
            throw new NullPointerException("Publisher pool is not initialize.");
        }
        publisherPool.publish(topic, payload, qos, isRetained);
    }

    /**
     * clear publisher pool
     */
    public synchronized void clearPublisherPool() {
        if (publisherPool == null) {
            throw new NullPointerException("Publisher pool is not initialize.");
        }
        publisherPool.clearPool();
    }
}
