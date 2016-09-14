package org.iotlabs.communication.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.iotlabs.models.mqtt.MqttMsg;

import java.util.UUID;

/**
 * MQTT Broker 의 메세지를 수신한다.
 * @author JongKwang
 *
 */
public class PahoReceiver {

    private MqttClient mqttClient;

    private final static String topic = "/topic/security";
    private final static int qos = 1;


    public static void main(String[] args) {
        String MqttBlockerURI = "tcp://127.0.0.1:1883"; // ./gradlew start_mqtt_broker
        String MqttClientId = UUID.randomUUID().toString();

        PahoReceiver pahoReceiver = new PahoReceiver(MqttBlockerURI, MqttClientId);
        pahoReceiver.receive();
    }

    public PahoReceiver(String MqttBlockerURI, String MqttClientId) {
        try {
            mqttClient = new MqttClient(MqttBlockerURI, MqttClientId);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            mqttClient.connect();
            mqttClient.setCallback(new MqttCallback() {

                /**
                 * This method is called when the connection to the server is lost.
                 */
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("### It have lost the connection to the Broker");
                }

                /**
                 * Called when delivery for a message has been completed, and all acknowledgments have been received.
                 */
                @Override
                public void deliveryComplete(IMqttDeliveryToken arg0) {
                    System.out.println("### It have lost the connection to the Broker");
                }

                /**
                 * This method is called when a message arrives from the server.
                 */
                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println(new MqttMsg(topic, mqttMessage).toJsonString());
               }
            });
            mqttClient.subscribe(topic, qos);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}

