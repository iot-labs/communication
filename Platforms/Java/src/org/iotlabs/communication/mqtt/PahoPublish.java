package org.iotlabs.communication.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * MQTT 서버에 메세지를 보내는 Publish 역할을 수행 한다.
 * @author JongKwang
 *
 */
public class PahoPublish {
	
	private MqttClient mqttClient;
	private MqttConnectOptions mqttConnectOpts;
	

	public PahoPublish(String MqttBlockerURI, String MqttClientId, String username, String password) {
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
		String MqttBlockerURI = "tcp://127.0.0.1:1883";	// ex) "tcp://127.0.0.1:1883"
		String MqttClientId = "MyClient_01";			// ex) "MyClient_01"
		String username = "user";						// ex) user (Bitnami RabbitMQ 의 기본 username 은 "user" 이다)
		String password = "bitnami";					// ex) bitnami (Bitnami RabbitMQ 의 기본 password 는 "bitnami" 이다)
		String topic = "/topic/security";
		String message = "Hello MQTT";
		
		PahoPublish publish = new PahoPublish(MqttBlockerURI, MqttClientId, username, password);
	}
	
	public void publish(String topic, String message) {
		MqttMessage mqttMessage = new MqttMessage();
		mqttMessage.setPayload(message.getBytes());
		
		try {
			mqttClient.publish("/topic/security", mqttMessage);
			mqttClient.disconnect();
			
		} catch (MqttException e) {
			e.printStackTrace();
		}
	}



}