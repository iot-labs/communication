package org.iotlabs.communication.mqtt;

import io.moquette.interception.InterceptHandler;
import io.moquette.server.Server;
import io.moquette.server.config.ClasspathConfig;
import io.moquette.server.config.IConfig;

import java.io.IOException;
import java.util.List;

/**
 * simple mqtt broker server
 * @see <a href="https://github.com/andsel/moquette">Java MQTT lightweight broker</a>
 */
public class SimpleMqttBroker {

    private static class Holder {
        private static final SimpleMqttBroker SIMPLE_MQTT_BROKER = new SimpleMqttBroker();
    }
    public static SimpleMqttBroker getInstance() {
        return Holder.SIMPLE_MQTT_BROKER;
    }

    private IConfig classPathConfig;
    private Server mqttBroker;

    private SimpleMqttBroker() {
        classPathConfig = new ClasspathConfig();
        mqttBroker = new Server();
    }

    /**
     * start simple mqtt server
     * @param interceptHandlers handlers when message published/subscribed
     * @throws IOException
     */
    public void start(List<? extends InterceptHandler> interceptHandlers) throws IOException{
        mqttBroker.startServer(classPathConfig, interceptHandlers);
        System.out.println("MQTT Broker started.");
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Stopping broker");
                mqttBroker.stopServer();
                System.out.println("Broker stopped");
            }
        });
    }
}
