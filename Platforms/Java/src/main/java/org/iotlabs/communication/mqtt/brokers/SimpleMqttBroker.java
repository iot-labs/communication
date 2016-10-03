package org.iotlabs.communication.mqtt.brokers;

import io.moquette.interception.InterceptHandler;
import io.moquette.interception.messages.*;
import io.moquette.server.Server;
import org.apache.log4j.Logger;
import org.iotlabs.communication.mqtt.MqttConfiguration;
import org.iotlabs.communication.mqtt.brokers.interceptors.LogInterceptHandler;
import org.iotlabs.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * simple mqtt broker server
 * @see <a href="https://github.com/andsel/moquette">Java MQTT lightweight broker</a>
 */
public class SimpleMqttBroker {

    private static final Logger logger = Logger.getLogger(SimpleMqttBroker.class);

    private final MqttConfiguration config;
    private final Server mqttBroker;

    public SimpleMqttBroker(String configPath) {
        config = new MqttConfiguration(configPath);
        mqttBroker = new Server();
    }

    /**
     * start simple mqtt server
     * @throws IOException throw at {@link io.moquette.server.netty.NettyAcceptor}
     */
    public void start() {
        List<InterceptHandler> interceptHandlers = Collections.emptyList();

        boolean isVerbose = config.getConfiguration().getBoolean("verbose", false);
        if (isVerbose) {
            interceptHandlers = new ArrayList<>();
            interceptHandlers.add(new LogInterceptHandler());
        }

        try {
            mqttBroker.startServer(config, interceptHandlers);
        } catch (IOException e) {
            logger.error("Start mqtt broker.", e);
        }
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

    /**
     * stop simple mqtt server.
     */
    public void stop() {
        logger.info("Stop mqtt broker.");
        mqttBroker.stopServer();
    }
}
