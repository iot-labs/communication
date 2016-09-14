package org.iotlabs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * Simple Properties Reader for general purpose.
 */
public class Configuration {

    private final Properties prop = new Properties();

    public static final String TRUE_AS_STRING = "true";

    public Configuration(String filename) throws IOException, NullPointerException{
        FileInputStream configFileInputStream = null;
        URL configFileUrl = getClass().getClassLoader().getResource(filename);
        if (configFileUrl == null) {
            throw new NullPointerException(filename + " is not exist.");
        }
        configFileInputStream = new FileInputStream(configFileUrl.getFile());
        try {
            prop.load(configFileInputStream);
        } finally {
            configFileInputStream.close();
        }
    }

    public Object setProperty(String key, String value) {
        return prop.setProperty(key, value);
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public String getProperty(String key, String def) {
        return prop.getProperty(key, def);
    }

    public boolean getBoolean(String key) {
        return TRUE_AS_STRING.equals(prop.getProperty(key));
    }

    public boolean getBoolean(String key, boolean def) {
        if (prop.containsKey(key)) {
            return getBoolean(key);
        } else {
            return def;
        }
    }
}
