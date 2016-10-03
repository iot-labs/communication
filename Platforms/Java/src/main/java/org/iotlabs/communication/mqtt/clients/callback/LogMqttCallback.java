package org.iotlabs.communication.mqtt.clients.callback;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.iotlabs.models.mqtt.MqttMsg;

public class LogMqttCallback extends SimpleMqttCallback {

    private static final Logger logger = Logger.getLogger(LogMqttCallback.class);

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logger.info(new MqttMsg(topic, message).toString());
    }
}
