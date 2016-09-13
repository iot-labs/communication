package org.iotlabs.models.mqtt.impl;

import org.iotlabs.models.BaseModel;

/**
 * Mqtt protocol message type.
 * <p>
 *     mqtt fixed header message type.
 * </p
 * @see <a href='http://public.dhe.ibm.com/software/dw/webservices/ws-mqtt/mqtt-v3r1.html#fixed-header'>fixed-header</a>
 *
 * @implNote Currently, we don't need to look mqtt type.
 * But, for whom wants to implement their own mqtt code, we have to store type information
 */
public class MqttType {
    public static final int RESERVED_0 = 0;
    public static final int CONNECT = 1;
    public static final int CONNACK = 2;
    public static final int PUBLISH = 3;
    public static final int PUBACK = 4;
    public static final int PUBREC = 5;
    public static final int PUBREL = 6;
    public static final int PUBCOMP = 7;
    public static final int SUBSCRIBE = 8;
    public static final int SUBACK = 9;
    public static final int UNSUBSCRIBE = 10;
    public static final int UNSUBACK = 11;
    public static final int PINGREQ = 12;
    public static final int PINGRESP = 13;
    public static final int DISCONNECT = 14;
    public static final int RESERVED_1 = 15;

    /**
     * Type which mqtt message created from library.
     * e.g.
     * {@link org.eclipse.paho.client.mqttv3.MqttMessage}
     * <b>
     *     Number means nothing.
     *     Never use this variable with bit operation.
     * </b>
     */
    public static final int UNKNOWN = BaseModel.VALUE_UNKNOWN;
}
