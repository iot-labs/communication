package org.iotlabs.models.mqtt.impl;

import com.google.gson.annotations.SerializedName;

/**
 * Mqtt protocol message qos.
 * <p>
 *     mqtt fixed header message qos.
 * </p>
 *
 * @see <a href='http://public.dhe.ibm.com/software/dw/webservices/ws-mqtt/mqtt-v3r1.html#fixed-header'>fixed-header</a>
 *
 */
public enum  MqttQos {
    @SerializedName("0")
    QOS_0,
    @SerializedName("1")
    QOS_1,
    @SerializedName("2")
    QOS_2;

    /**
     * Get {@link MqttQos} from integer value.
     * @param qos qos level [0,1,2].
     * @return If qos level is between 0~2, return proper MqttQos enum. Else return <b>QOS_0</b>.
     */
    public static MqttQos from(int qos) {
        switch (qos) {
            case 0:
                return QOS_0;
            case 1:
                return QOS_1;
            case 2:
                return QOS_2;
            default:
                return QOS_0;
        }
    }
}
