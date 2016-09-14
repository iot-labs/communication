package org.iotlabs.util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;


import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashMap;
import java.util.Map;

public class ConfigurationTest {

    private Configuration configurationFromFile;
    private Configuration configurationFromMap;
    private static Map<String, Object> propMap;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @BeforeClass
    public static void beforeClass() {
        propMap = new HashMap<>();
        propMap.put("user_name", "tester");
        propMap.put("user_age", 17);
        propMap.put("user_ip", "0_0_0_0");
        propMap.put("is_user_male", false);
    }

    @Before
    public void setUp() throws Exception {
        configurationFromFile = new Configuration("test.properties.txt");
        configurationFromMap = new Configuration(propMap);
    }

    @Test(expected=FileAlreadyExistsException.class)
    public void storeExistFile() throws Exception {
        configurationFromFile.store("test.properties.txt", "test comment");
    }

    @Test(expected=FileNotFoundException.class)
    public void storeFileNameEmpty() throws Exception {
        configurationFromMap.store("", "test comment");
    }

    @Test
    public void storeFile() throws Exception {
        String filePath = temporaryFolder.newFile("new_test.properties").getAbsolutePath();
        configurationFromMap.store(filePath, "test comment");
        configurationFromFile = new Configuration(filePath, false);
        assertEquals("tester", configurationFromFile.getString("user_name"));
    }

    @Test
    public void setProperty() throws Exception {
        configurationFromMap.setProperty("test_key", "test_key");
        assertEquals("test_key", configurationFromMap.getString("test_key"));
        configurationFromMap.setBoolean("new_boolean_false", false);
        assertEquals(false, configurationFromMap.getBoolean("new_boolean_false"));
        configurationFromMap.setInteger("new_int_one", 1);
        assertEquals(1, configurationFromMap.getInteger("new_int_one"));
    }

    @Test
    public void getProperty() throws Exception {
        // simple get test
        assertEquals("tester", configurationFromMap.getString("user_name"));
        assertEquals("not_exist", configurationFromMap.getString("not_exist", "not_exist"));
        // simple getBoolean test
        assertEquals(false, configurationFromMap.getBoolean("is_user_male"));
        assertEquals(true, configurationFromMap.getBoolean("not_exist", true));
        assertEquals("false", configurationFromMap.getString("is_user_male"));
        // simple getInteger test
        assertEquals(17, configurationFromMap.getInteger("user_age"));
        assertEquals(1, configurationFromMap.getInteger("not_exist", 1));
        assertEquals("17", configurationFromMap.getString("user_age"));

    }
}