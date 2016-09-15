package org.iotlabs.communication.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.UUID;

/**
 * MQTT 서버에 메세지를 보내는 Publish 역할을 수행 한다.
 * @author JongKwang
 *
 */
public class PahoPublish {

    private MqttClient mqttClient;

    public static void main(String[] args) {
        String MqttBlockerURI = "tcp://127.0.0.1:1883"; // ./gradlew start_mqtt_broker
        String MqttClientId = UUID.randomUUID().toString();
        String topic = "/sensor/test";
        String message = "Hello MQTT";

        PahoPublish pahoPublish = new PahoPublish(MqttBlockerURI, MqttClientId);
        pahoPublish.publish(topic, message);
    }

    public PahoPublish(String MqttBlockerURI, String MqttClientId) {
        try {
            mqttClient = new MqttClient(MqttBlockerURI, MqttClientId);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void publish(String topic, String message) {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setPayload(message.getBytes());

        mqttMessage.setQos(0);
        mqttMessage.setRetained(true);

        try {
            mqttClient.connect();
            mqttClient.publish(topic, mqttMessage);
            mqttClient.disconnect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
