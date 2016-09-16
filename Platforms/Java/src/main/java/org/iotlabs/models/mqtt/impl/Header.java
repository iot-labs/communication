package org.iotlabs.models.mqtt.impl;

import com.google.gson.annotations.SerializedName;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.iotlabs.models.BaseModel;

/**
 * Mqtt Fixed Header
 * <p>
 *     Bean for Mqtt Fixed Header part.
 * </p>
 *
 */
public class Header extends BaseModel {

    /**
     * mqtt fixed header message type
     */
    @SerializedName("type")
    private int type;

    /**
     * mqtt message qos
     */
    @SerializedName("qos")
    private MqttQos qos;

    /**
     * mqtt fixed header duplicate flag.
     */
    @SerializedName("dup")
    private boolean dup = false;

    /**
     * mqtt fixed header retained flag.
     */
    @SerializedName("retained")
    private boolean retained = false;

    /**
     * mqtt remaining length
     *
     * @implNote Currently, we don't need to look remaining length.
     * This variable is for those who wants to implement their own mqtt code.
     */
    @SerializedName("remaining_length")
    private int remainingLength;

    public Header(MqttMessage pahoMqttMessage) {
        this.type = MqttType.UNKNOWN;
        this.qos = MqttQos.from(pahoMqttMessage.getQos());
        this.dup = pahoMqttMessage.isDuplicate();
        this.retained = pahoMqttMessage.isRetained();
        this.remainingLength = VALUE_UNKNOWN;
    }


    public int getType() {
        return type;
    }

    public MqttQos getQos() {
        return qos;
    }

    public boolean isDup() {
        return dup;
    }

    public boolean isRetained() {
        return retained;
    }

    public int getRemainingLength() {
        return remainingLength;
    }

    @Override
    public String toString() {
        return "Header{" +
                "type=" + type +
                ", qos=" + qos +
                ", dup=" + dup +
                ", retained=" + retained +
                ", remainingLength=" + remainingLength +
                '}';
    }
}
