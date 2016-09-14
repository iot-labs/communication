package org.iotlabs.communication.mqtt;

import io.moquette.server.config.IConfig;
import org.apache.log4j.Logger;
import org.iotlabs.util.Configuration;

import java.io.IOException;

/**
 * Wrapper class of {@link IConfig}.
 * Moquette's configuration(i.e. properties file) format is not standard (split with white space).
 * So, We create wrapper class that split key and value by `equal(=)`.
 *
 */
public class MqttConfiguration implements IConfig {

    private Configuration configuration;

    public MqttConfiguration(String filename) {
        try {
            configuration = new Configuration(filename);
        } catch (IOException e) {
            Logger.getLogger(getClass()).error("Fail on create MqttConfiguration.", e);
            e.printStackTrace();
        }
    }

    @Override
    public void setProperty(String name, String value) {
        configuration.setProperty(name, value);
    }

    @Override
    public String getProperty(String name) {
        return configuration.getString(name);
    }

    @Override
    public String getProperty(String name, String defaultValue) {
        return configuration.getString(name, defaultValue);
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
