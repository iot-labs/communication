package org.iotlabs.webserver.blueprints;

import org.iotlabs.communication.mqtt.MqttProxy;

import static spark.Spark.get;

public class MqttBluePrint extends AbstractBluePrint {

    public MqttBluePrint() {
        super("mqtt");
        // must init mqtt publisher pool
        MqttProxy.getInstance().initPublisherPool(5, "tcp://localhost:1883", false);
    }

    /**
     * register mqtt GET endpoint
     * get(endpoint("publish")
     *  http://localhost:4567/mqtt/publish?topic=test&client_id=17&payload=hi%20test
     *  mqtt topic : /test/17    * /{topic}/{client_id}
     *  mqtt payload : hi test   * {payload}
     */
    @Override
    public void register() {
        get(getEndPoint("publish"), (req, res) -> {
            String clientId = req.queryParams("client_id");
            String topic = req.queryParams("topic");
            String payload = req.queryParams("payload");
            if (!topic.startsWith("/")) {
                topic = "/" + topic;
            }
            MqttProxy.getInstance().publish(topic+"/"+clientId, payload, 1, false);
            return "send";
        });
    }
}
