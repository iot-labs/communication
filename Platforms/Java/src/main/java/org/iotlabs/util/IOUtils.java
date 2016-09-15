package org.iotlabs.util;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttAsyncClient;
import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.io.Closeable;
import java.io.IOException;

/**
 * Utility class for IO stuff.
 *
 */
public class IOUtils {
    private static final Logger logger = Logger.getLogger(IOUtils.class);

    /**
     * Close streams quietly.
     * @param closeable anything closeable
     */
    public static void closeQuite(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // close quite
                logger.info("Close quite.", e);
            }
        }
    }

    /**
     * close iMqttAsyncClient quietly.
     * @param iMqttAsyncClient mqtt async client interface.
     */
    public static void closeMqttClientQuite(IMqttAsyncClient iMqttAsyncClient) {
        if (iMqttAsyncClient != null) {
            try {
                iMqttAsyncClient.disconnectForcibly();
            } catch (MqttException e) {
                logger.info("Close Mqtt Client quite.", e);
            }
        }
    }

    /**
     * close iMqttClient quietly.
     * @param iMqttClient mqtt client interface.
     */
    public static void closeMqttClientQuite(IMqttClient iMqttClient) {
        if (iMqttClient != null) {
            try {
                iMqttClient.disconnectForcibly();
            } catch (MqttException e) {
                logger.info("Close Mqtt Client quite.", e);
            }
        }
    }
}
