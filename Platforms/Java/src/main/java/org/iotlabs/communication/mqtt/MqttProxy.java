package org.iotlabs.communication.mqtt;

import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import io.moquette.interception.InterceptHandler;
import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.iotlabs.models.BaseModel;
import org.iotlabs.models.mqtt.MqttMsg;
import org.iotlabs.models.mqtt.ReceiverPreference;
import org.iotlabs.util.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

public class MqttProxy {
    private static final Logger logger = Logger.getLogger(MqttProxy.class);

    private static class Holder {
        public static final MqttProxy MQTT_PROXY = new MqttProxy();
    }

    public static MqttProxy getInstance() {
        return Holder.MQTT_PROXY;
    }

    private MqttProxy() {

    }

    public void startMqttBroker(List<InterceptHandler> interceptHandlers) {
        try {
            SimpleMqttBroker.getInstance().start(interceptHandlers);
        } catch (IOException e) {
            logger.error("Start mqtt broker.", e);
        }
    }

    public void registerReceivers(String receiverPreferenceFile) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(
                    new InputStreamReader(new FileInputStream(receiverPreferenceFile), Charset.forName("UTF-8"))
            );
            ReceiverPreferences receiverPreferences = BaseModel.fromJsonString(reader, ReceiverPreferences.class);
            registerReceivers(receiverPreferences.getReceiverPreferenceList());
        } catch (FileNotFoundException e) {
            logger.error("Receiver file " + receiverPreferenceFile + " not found.", e);
        } finally {
            logger.info("Receivers in " + receiverPreferenceFile + " registered.");
            IOUtils.closeQuite(reader);
        }
    }

    private void registerReceivers(Collection<ReceiverPreference> receiverPreferences) {
        for(ReceiverPreference receiverPreference : receiverPreferences) {
            try {
                AsyncReceiver asyncReceiver = new AsyncReceiver(receiverPreference);
                // asyncReceiver.subscribeAsync(null);
                // FIXME for debug
                asyncReceiver.subscribeAsync(new SimpleMqttCallback() {
                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        logger.info(new MqttMsg(topic, message).toString());
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }

        }
    }

    private static class ReceiverPreferences extends BaseModel {
        @SerializedName("receivers")
        List<ReceiverPreference> receiverPreferenceList;

        private List<ReceiverPreference> getReceiverPreferenceList() {
            return receiverPreferenceList;
        }
    }

    private static class SimpleMqttCallback implements MqttCallback {
        @Override
        public void connectionLost(Throwable cause) {

        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {

        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {

        }
    }
}
