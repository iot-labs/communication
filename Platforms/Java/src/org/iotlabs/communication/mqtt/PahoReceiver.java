package org.iotlabs.communication.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * MQTT Brocker 의 메세지를 수신한다.
 * @author JongKwang
 *
 */
public class PahoReceiver {

	private MqttClient mqttClient;
	private MqttConnectOptions mqttConnectOpts;
	
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
	}
}

