package org.iotlabs.communication.mqtt.clients;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.iotlabs.communication.mqtt.MqttProxy;
import org.iotlabs.communication.mqtt.clients.callback.SimpleMqttCallback;
import org.iotlabs.models.mqtt.MqttMsg;
import org.iotlabs.models.mqtt.recievers.Receivers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class BaseClientTest {

    @BeforeClass
    public static void beforeClass() {
        MqttProxy.getInstance().startMqttBroker("test_mqtt_broker.conf");
    }

    @AfterClass
    public static void afterClass() {
        MqttProxy.getInstance().stopMqttBroker();
    }

    @Test
    public void subscribeAndPublish() throws Exception {
        Map<String, String> topicPayloadMap = new HashMap<>();
        topicPayloadMap.put("/sensor/test", "test_one");
        topicPayloadMap.put("/sensor/test1", "test_two");
        CountDownLatch countDownLatch = new CountDownLatch(topicPayloadMap.size());

        Receivers receivers = MqttProxy.getInstance().getReceiversFromFile(new File(getClass().getClassLoader().getResource("test_mqtt_receiver.json").toURI()).getAbsolutePath());
        MqttProxy.getInstance().registerReceivers(
                receivers,
                new SimpleMqttCallback() {
                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        MqttMsg mqttMsg = new MqttMsg(topic, message);
                        assertEquals(topicPayloadMap.get(topic), mqttMsg.getPayload());
                        countDownLatch.countDown();
                    }
                },
                true
        );
        // force sleep for 0.5 second
        // on my MBA 2014 early with 8GB ram,
        // start mqtt broker and register receiver took some time.
        Thread.sleep(500);
        // When testing, async publisher not working very well. So, use sync publisher.
        MqttProxy.getInstance().initPublisherPool(5, "tcp://localhost:1883", true, true);

        for (Map.Entry<String, String> entry : topicPayloadMap.entrySet()) {
            MqttProxy.getInstance().publish(entry.getKey(), entry.getValue(), 1, false);
        }
        countDownLatch.await(5000, TimeUnit.MILLISECONDS);
        MqttProxy.getInstance().clearPublisherPool();
        if (countDownLatch.getCount() > 0) {
            assertEquals(true, false);
        }
    }
}