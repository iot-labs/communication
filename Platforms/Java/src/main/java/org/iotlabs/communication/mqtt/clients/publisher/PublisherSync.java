package org.iotlabs.communication.mqtt.clients.publisher;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.iotlabs.communication.mqtt.clients.BaseClient;
import org.iotlabs.communication.mqtt.clients.BaseSyncClient;
import org.iotlabs.util.IOUtils;
import org.iotlabs.util.StringUtils;

/**
 * publish signal synchronously
 */
public class PublisherSync extends BaseSyncClient implements BaseClient {

    public PublisherSync(String brokerUrl, String clientId) throws MqttException {
        super(brokerUrl, clientId);
        mMqttClient.connect();
    }

    @Override
    public void publish(String topic, String payload, int qos, boolean isRetained) throws MqttException {
        mMqttClient.publish(topic, payload.getBytes(StringUtils.CHARSET), qos, isRetained);
    }

    @Override
    public void disconnect() {
        IOUtils.closeMqttClientQuite(mMqttClient);
    }
}
