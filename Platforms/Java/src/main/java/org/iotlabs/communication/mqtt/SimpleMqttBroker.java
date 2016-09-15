package org.iotlabs.communication.mqtt;

import io.moquette.interception.InterceptHandler;
import io.moquette.interception.messages.*;
import io.moquette.server.Server;
import org.apache.log4j.Logger;
import org.iotlabs.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * simple mqtt broker server
 * @see <a href="https://github.com/andsel/moquette">Java MQTT lightweight broker</a>
 */
public class SimpleMqttBroker {

    private static final Logger logger = Logger.getLogger(SimpleMqttBroker.class);

    private static class Holder {
        private static final SimpleMqttBroker SIMPLE_MQTT_BROKER = new SimpleMqttBroker();
    }
    public static SimpleMqttBroker getInstance() {
        return Holder.SIMPLE_MQTT_BROKER;
    }

    private final MqttConfiguration config;
    private final Server mqttBroker;
    private final boolean isVerbose;

    private SimpleMqttBroker() {
        config = new MqttConfiguration("config/moquette.conf");
        mqttBroker = new Server();

        isVerbose = config.getConfiguration().getBoolean("verbose", false);
    }

    /**
     * start simple mqtt server
     * @param interceptHandlers handlers when message published/subscribed
     * @throws IOException throw at {@link io.moquette.server.netty.NettyAcceptor}
     */
    public void start(List<InterceptHandler> interceptHandlers) throws IOException{
        if (interceptHandlers == null) {
            interceptHandlers = new ArrayList<>();
        }

        if (isVerbose) {
            interceptHandlers.add(new LogInterceptHandler());
        }

        mqttBroker.startServer(config, interceptHandlers);
        System.out.println("MQTT Broker started.");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("Stopping broker");
                mqttBroker.stopServer();
                logger.info("Broker stopped");
            }
        });
    }

    private static class LogInterceptHandler implements InterceptHandler {

        @Override
        public void onPublish(InterceptPublishMessage msg) {
            System.out.println("== Publish Event Received ==");
            System.out.println("From : " + msg.getClientID());
            System.out.println("Topic : " + msg.getTopicName());
            System.out.println("Content : " + StringUtils.getString(msg.getPayload().array()));
        }

        @Override
        public void onSubscribe(InterceptSubscribeMessage msg) {
            System.out.println("== Subscribe Event Received ==");
            System.out.println("From : " + msg.getClientID());
            System.out.println("Topic Filter : " + msg.getTopicFilter());
            System.out.println("Qos : " + msg.getRequestedQos());
        }

        @Override
        public void onConnect(InterceptConnectMessage msg) {
            System.out.println("== Connect Event Received ==");
            System.out.println("From : " + msg.getClientID());
        }

        @Override
        public void onDisconnect(InterceptDisconnectMessage msg) {
            System.out.println("== Disconnect Event Received ==");
            System.out.println("From : " + msg.getClientID());
        }

        @Override
        public void onUnsubscribe(InterceptUnsubscribeMessage msg) {
            System.out.println("== UnSubscribe Event Received ==");
            System.out.println("From : " + msg.getClientID());
            System.out.println("Topic Filter : " + msg.getTopicFilter());
        }
    }
}
