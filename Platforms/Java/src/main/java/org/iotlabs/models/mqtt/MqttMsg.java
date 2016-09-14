package org.iotlabs.models.mqtt;

import com.google.gson.annotations.SerializedName;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.iotlabs.models.BaseModel;
import org.iotlabs.models.mqtt.impl.Header;
import org.iotlabs.util.StringUtils;


public class MqttMsg extends BaseModel {

    @SerializedName("fixed_header")
    private Header header;
    @SerializedName("topic")
    private String topic;
    @SerializedName("payload")
    private String payload;

    public MqttMsg() { }

    public MqttMsg(String topic, MqttMessage pahoMqttMessage) {
        this.header = new Header(pahoMqttMessage);
        this.payload = StringUtils.getString(pahoMqttMessage.getPayload());
        this.topic = topic;
    }

    public Header getHeader() {
        return header;
    }

    public String getPayload() {
        return payload;
    }

    public String getTopic() {
        return topic;
    }
}
