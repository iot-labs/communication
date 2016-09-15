package org.iotlabs.communication.mqtt;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.iotlabs.models.mqtt.ReceiverPreference;
import org.iotlabs.util.IOUtils;

public class BaseAsyncClient {
    private static final Logger logger = Logger.getLogger(BaseAsyncClient.class);

    protected MqttAsyncClient mMqttAsyncClient;

    public BaseAsyncClient(ReceiverPreference receiverPreference) throws MqttException {
        mMqttAsyncClient = new MqttAsyncClient(receiverPreference.getBrokerUrl(), receiverPreference.getClientId());
        // add runtime hook.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Close " + receiverPreference.getClientId());
                IOUtils.closeMqttClientQuite(mMqttAsyncClient);
                logger.info(receiverPreference.getClientId() + " closed");
            }
        });
    }
}
