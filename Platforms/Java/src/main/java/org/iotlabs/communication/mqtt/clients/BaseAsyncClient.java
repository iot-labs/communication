package org.iotlabs.communication.mqtt.clients;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.iotlabs.util.IOUtils;
/*
 * the base class to communicate asynchronously with m
 */
public class BaseAsyncClient {
    private static final Logger logger = Logger.getLogger(BaseAsyncClient.class);

    protected MqttAsyncClient mMqttClient;

    public BaseAsyncClient(String brokerUrl, String clientId) throws MqttException {
        mMqttClient = new MqttAsyncClient(brokerUrl, clientId);
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
