package org.iotlabs.communication.mqtt.clients.receiver;

import io.moquette.proto.MQTTException;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.iotlabs.communication.mqtt.clients.BaseAsyncClient;
import org.iotlabs.models.mqtt.recievers.Receiver;
import org.iotlabs.util.IOUtils;

/**
 * receive mqtt signal Asynchronously
 */
public class ReceiverAsync extends BaseAsyncClient {

    private static Logger logger = Logger.getLogger(ReceiverAsync.class);

    private Receiver receiver;

    public ReceiverAsync(Receiver receiver) throws MqttException {
        super(receiver.getBrokerUrl(), receiver.getClientId());
        this.receiver = receiver;
    }

    public void subscribe(MqttCallback mqttCallback) throws MqttException {
        if (mqttCallback != null) {
            mMqttClient.setCallback(mqttCallback);
        }
        mMqttClient.connect(null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                try {
                    mMqttClient.subscribe(receiver.getTopic(), receiver.getQos());
                } catch (MqttException e) {
                    throw new MQTTException(e);
                }
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                throw new MQTTException(exception);
            }
        });
    }

    public void unsubscribe() {
        try {
            mMqttClient.unsubscribe(receiver.getTopic());
            IOUtils.closeMqttClientQuite(mMqttClient);
        } catch (MqttException e) {
            logger.error("Fail on un subscribe receiver.", e);
        }
    }
}
