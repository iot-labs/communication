package org.iotlabs.communication.mqtt.clients;

import io.moquette.proto.MQTTException;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;
import org.iotlabs.models.mqtt.ReceiverPreference;
import org.iotlabs.util.IOUtils;

/**
 * receive signal Asynchronously
 */
public class ReceiverAsync extends BaseAsyncClient {

    private static Logger logger = Logger.getLogger(ReceiverAsync.class);

    private ReceiverPreference receiverPreference;

    public ReceiverAsync(ReceiverPreference receiverPreference) throws MqttException {
        super(receiverPreference.getBrokerUrl(), receiverPreference.getClientId());
        this.receiverPreference = receiverPreference;
    }

    public void subscribeAsync(MqttCallback mqttCallback) throws MqttException {
        if (mMqttClient.isConnected()) {
            mMqttClient.disconnectForcibly();
        }
        if (mqttCallback != null) {
            mMqttClient.setCallback(mqttCallback);
        }
        mMqttClient.connect(null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                try {
                    mMqttClient.subscribe(receiverPreference.getTopic(), receiverPreference.getQos());
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

    public void unSubscribe() {
        try {
            mMqttClient.unsubscribe(receiverPreference.getTopic());
            IOUtils.closeMqttClientQuite(mMqttClient);
        } catch (MqttException e) {
            logger.error("Fail on un subscribe receiver.", e);
        }
    }
}
