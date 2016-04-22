package org.iotlabs.communication.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * MQTT Broker 의 메세지를 수신한다.
 * @author JongKwang
 *
 */
public class PahoReceiver {
	
	private MqttClient mqttClient;
	private MqttConnectOptions mqttConnectOpts;
	
	private final String topic = "/topic/security";
	private final int qos = 1;
	
	public PahoReceiver(String MqttBlockerURI, String MqttClientId, String username, String password) {
		try {
			mqttClient = new MqttClient(MqttBlockerURI, MqttClientId);

			mqttConnectOpts = new MqttConnectOptions();
			// mqttConnectOpts.setCleanSession(true);
			mqttConnectOpts.setUserName("user");
			mqttConnectOpts.setPassword("bitnami".toCharArray());

		} catch (MqttException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String MqttBlockerURI = "tcp://127.0.0.1:1883"; // ex) "tcp://127.0.0.1:1883"
		String MqttClientId = "MyClient_01"; 			// ex) "MyClient_01"
		String username = "user"; 						// ex) user (Bitnami RabbitMQ 의 기본 username 은 "user" 이다)
		String password = "bitnami"; 					// ex) bitnami (Bitnami RabbitMQ 의 기본 password 는 "bitnami" 이다)

		PahoReceiver pahoReceiver = new PahoReceiver(MqttBlockerURI, MqttClientId, username, password);
		pahoReceiver.receive();
	}
	
	public void receive() {
		try {
			mqttClient.connect(mqttConnectOpts);
			mqttClient.setCallback(new MqttCallback() {

				@Override
				/**
				 * This method is called when the connection to the server is lost.
				 */
				public void connectionLost(Throwable cause) {
					System.out.println("### It have lost the connection to the Broker");
				}

				@Override
				/**
				 * Called when delivery for a message has been completed, and all acknowledgments have been received.
				 */
				public void deliveryComplete(IMqttDeliveryToken arg0) {
					System.out.println("### It have lost the connection to the Broker");
				}

				@Override
				/**
				 * This method is called when a message arrives from the server.
				 */
				public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
					System.out.println(arg0 + ": " + arg1.toString());

				}
			}); // close setCallback()
			
			mqttClient.subscribe(topic, qos);
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

}
