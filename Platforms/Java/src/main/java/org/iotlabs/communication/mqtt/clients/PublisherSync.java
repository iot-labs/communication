package org.iotlabs.communication.mqtt.clients;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.iotlabs.util.IOUtils;
import org.iotlabs.util.StringUtils;

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
