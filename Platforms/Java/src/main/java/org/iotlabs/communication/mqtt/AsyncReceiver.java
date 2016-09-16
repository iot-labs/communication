package org.iotlabs.communication.mqtt;

import io.moquette.proto.MQTTException;
import org.eclipse.paho.client.mqttv3.*;
import org.iotlabs.models.mqtt.ReceiverPreference;

public class AsyncReceiver extends BaseAsyncClient {

    private ReceiverPreference receiverPreference;

    public AsyncReceiver(ReceiverPreference receiverPreference) throws MqttException {
        super(receiverPreference);
        this.receiverPreference = receiverPreference;
    }

    public void subscribeAsync(MqttCallback mqttCallback) throws MqttException {
        if (mMqttAsyncClient.isConnected()) {
            mMqttAsyncClient.disconnectForcibly();
        }
        if (mqttCallback != null) {
            mMqttAsyncClient.setCallback(mqttCallback);
        }
        mMqttAsyncClient.connect(null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                try {
                    mMqttAsyncClient.subscribe(receiverPreference.getTopic(), receiverPreference.getQos());
                } catch (MqttException e) {
                    throw new MQTTException(e);
                }
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

            }
        });

    }
}
