package org.iotlabs.communication.mqtt.clients;

import org.eclipse.paho.client.mqttv3.MqttException;
/*
 * the base interface to communicate with mqtt server
 */
public interface BaseClient {
    void publish(String topic, String payload, int qos, boolean isRetained) throws MqttException;
    void disconnect();
}
