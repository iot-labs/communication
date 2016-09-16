package org.iotlabs.communication.mqtt;

import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import io.moquette.interception.InterceptHandler;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.iotlabs.communication.mqtt.brokers.SimpleMqttBroker;
import org.iotlabs.communication.mqtt.clients.ReceiverAsync;
import org.iotlabs.models.BaseModel;
import org.iotlabs.models.mqtt.MqttMsg;
import org.iotlabs.models.mqtt.ReceiverPreference;
import org.iotlabs.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;




public class MqttProxy {
    private static final Logger logger = Logger.getLogger(MqttProxy.class);

    private static class Holder {
        public static final MqttProxy MQTT_PROXY = new MqttProxy();
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

    private MqttProxy() {
        receiverMap = new ConcurrentHashMap<>();
    }

    public void startMqttBroker(List<InterceptHandler> interceptHandlers) {
        try {
            SimpleMqttBroker.getInstance().start(interceptHandlers);
        } catch (IOException e) {
            logger.error("Start mqtt broker.", e);
        }
    }

    public void stopMqttBroker() {
        SimpleMqttBroker.getInstance().stop();
    }

    public void registerReceivers(String receiverPreferenceFile, MqttCallback mqttCallback) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(
                    new InputStreamReader(new FileInputStream(receiverPreferenceFile), Charset.forName("UTF-8"))
            );
            ReceiverPreferences receiverPreferences = BaseModel.fromJsonString(reader, ReceiverPreferences.class);
            registerReceivers(receiverPreferences.getReceiverPreferenceList(), mqttCallback);
        } catch (FileNotFoundException e) {
            logger.error("Receiver file " + receiverPreferenceFile + " not found.", e);
        } finally {
            logger.info("Receivers in " + receiverPreferenceFile + " registered.");
            IOUtils.closeQuite(reader);
        }
    }

    private void registerReceivers(Collection<ReceiverPreference> receiverPreferences, MqttCallback mqttCallback) {
        for(ReceiverPreference receiverPreference : receiverPreferences) {
            try {
                ReceiverAsync receiverAsync = new ReceiverAsync(receiverPreference);
                receiverMap.put(receiverPreference.getClientId(), receiverAsync);
                if (mqttCallback != null) {
                    receiverAsync.subscribeAsync(mqttCallback);
                } else {
                    // FIXME for debug
                    // receiverAsync.subscribeAsync(null);
                    receiverAsync.subscribeAsync(new SimpleMqttCallback() {
                        @Override
                        public void messageArrived(String topic, MqttMessage message) throws Exception {
                            logger.info(new MqttMsg(topic, message).toString());
                        }
                    });
                }
            } catch (MqttException e) {
                e.printStackTrace();
            }

        }
    }

    public synchronized void unsubscribeReceiver(String clientId) {
        ReceiverAsync receiverAsync = receiverMap.get(clientId);
        if (receiverAsync != null) {
            receiverAsync.unSubscribe();
        }
        receiverMap.remove(clientId);
    }

    public void initPublisherPool(int poolSize, String brokerUrl, boolean isSync) {
        publisherPool = new PublisherPool(poolSize, brokerUrl, isSync);
    }

    public void publish(String topic, String payload, int qos, boolean isRetained) {
        if (publisherPool == null) {
            throw new NullPointerException("Publisher pool is not initialize.");
        }
        publisherPool.publish(topic, payload, qos, isRetained);
    }

    public void clearPublisherPool() {
        if (publisherPool == null) {
            throw new NullPointerException("Publisher pool is not initialize.");
        }
        publisherPool.clearPool();
    }

    private static class ReceiverPreferences extends BaseModel {
        @SerializedName("receivers")
        List<ReceiverPreference> receiverPreferenceList;

        private List<ReceiverPreference> getReceiverPreferenceList() {
            return receiverPreferenceList;
        }
    }

    public static class SimpleMqttCallback implements MqttCallback {
        @Override
        public void connectionLost(Throwable cause) {

        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {

        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }
    }

}
