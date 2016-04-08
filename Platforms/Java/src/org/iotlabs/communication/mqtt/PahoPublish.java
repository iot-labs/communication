package org.iotlabs.communication.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

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
	}
	
}