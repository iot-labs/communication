package org.iotlabs.communication.mqtt.clients;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.iotlabs.util.IOUtils;

public class BaseSyncClient {
    private static final Logger logger = Logger.getLogger(BaseSyncClient.class);

    protected MqttClient mMqttClient;
    public BaseSyncClient(String brokerUrl, String clientId) throws MqttException {
        mMqttClient = new MqttClient(brokerUrl, clientId);
        // add runtime hook.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Close " + clientId);
                IOUtils.closeMqttClientQuite(mMqttClient);
                logger.info(clientId + " closed");
            }
        });
    }
}
