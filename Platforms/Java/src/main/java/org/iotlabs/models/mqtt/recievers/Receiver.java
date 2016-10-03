package org.iotlabs.models.mqtt.recievers;

import com.google.gson.annotations.SerializedName;
import org.iotlabs.models.BaseModel;

public class Receiver extends BaseModel {

    @SerializedName("broker_url")
    private String brokerUrl;

    @SerializedName("client_id")
    private String clientId;

    @SerializedName("topic")
    private String topic;

    @SerializedName("qos")
    private int qos;

    @SerializedName("is_persist")
    private boolean isPersist;

    public String getBrokerUrl() {
        return brokerUrl;
    }

    public String getClientId() {
        return clientId;
    }

    public String getTopic() {
        return topic;
    }

    public int getQos() {
        return qos;
    }

    public boolean isPersist() {
        return isPersist;
    }
}
