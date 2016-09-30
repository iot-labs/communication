package org.iotlabs.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;
import java.util.Properties;

/**
 * Simple Properties Reader for general purpose.
 */
public class Configuration {
    private static final Logger logger = Logger.getLogger(Configuration.class);

    private final Properties prop = new Properties();

    private static final String TRUE_AS_STRING = "true";
    private static final String FALSE_AS_STRING = "false";

    private String filename;

    /**
     * Create configuration from file under resources folder.
     * @param resourceFileName filename under `resources` folder.
     * @throws IOException
     * @throws NullPointerException
     */
    public Configuration(String resourceFileName) throws IOException, NullPointerException {
        this(resourceFileName, true);
    }

    public Configuration(String filename, boolean isFromResource) throws IOException, NullPointerException {
        this.filename = filename;
        if (isFromResource) {
            loadFromFileResources();
        } else {
            loadFile();
        }
    }

    /**
     * Create configuration from map.
     * <b>All value will convert to string.</b>
     * @param properties map contains properties.
     */
    public Configuration(Map<String, Object> properties) {
        this.filename = null;
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            prop.put(entry.getKey(), String.valueOf(entry.getValue()));
        }
    }

    /**
     * load properties from file `resources` directory.
     * @throws IOException
     * @throws FileNotFoundException when file not exist
     */
    private void loadFromFileResources() throws IOException, FileNotFoundException {
        FileInputStream configFileInputStream = null;
        URL configFileUrl = getClass().getClassLoader().getResource(filename);
        if (configFileUrl == null) {
            throw new FileNotFoundException(filename + " is not exist.");
        }
        configFileInputStream = new FileInputStream(configFileUrl.getFile());
        try {
            prop.load(configFileInputStream);
        } finally {
            IOUtils.closeQuite(configFileInputStream);
        }
    }

    /**
     * load property from file.
     * @throws IOException
     */
    private void loadFile() throws IOException {
        FileInputStream configFileInputStream = null;
        try {
            configFileInputStream = new FileInputStream(filename);
            prop.load(configFileInputStream);
        } finally {
            IOUtils.closeQuite(configFileInputStream);
        }
    }

    /**
     * Store current properties to file.
     * Always overwrite.
     * @param filenameToSave file to save
     * @param comments comments on property file
     * @throws FileNotFoundException filenameToSave is empty
     * @throws FileAlreadyExistsException filenameToSave already exist
     */
    public void store(String filenameToSave, String comments)
            throws FileNotFoundException, FileAlreadyExistsException {
        if (StringUtils.isEmpty(filenameToSave)) {
            throw new FileNotFoundException();
        }
        if (filenameToSave.equals(filename)) {
            throw new FileAlreadyExistsException(filenameToSave);
        }
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(filenameToSave, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            prop.store(bufferedWriter, comments);
        } catch (IOException e) {
            logger.error("Fail on store properties.", e);
        } finally {
            IOUtils.closeQuite(fileWriter);
            IOUtils.closeQuite(bufferedWriter);
        }
    }


    /* property read/write */

    public Object setProperty(String key, String value) {
        return prop.setProperty(key, value);
    }

    public String getString(String key) {
        return prop.getProperty(key);
    }

    public String getString(String key, String def) {
        return prop.getProperty(key, def);
    }

    public void setBoolean(String key, boolean value) {
        prop.setProperty(key, value ? TRUE_AS_STRING : FALSE_AS_STRING);
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(prop.getProperty(key));
    }

    public boolean getBoolean(String key, boolean def) {
        if (prop.containsKey(key)) {
            return getBoolean(key);
        } else {
            return def;
        }
    }

    public void setInteger(String key, int value) {
        prop.setProperty(key, String.valueOf(value));
    }

    public int getInteger(String key) {
        return Integer.parseInt(prop.getProperty(key));
    }

    public int getInteger(String key, int def) {
        if (prop.containsKey(key)) {
            return getInteger(key);
        } else {
            return def;
        }
    }
}
