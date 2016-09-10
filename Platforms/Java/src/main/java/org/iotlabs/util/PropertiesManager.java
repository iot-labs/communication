package org.iotlabs.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesManager {
    private String propertiesFilePath;

    public PropertiesManager(String propertiesFilePath) {
        this.propertiesFilePath = propertiesFilePath;
    }

    public String getKey(String key) {

        String value = null;
        InputStream is = null;
        Properties p = null;
        try {
            is = new FileInputStream(propertiesFilePath);
            p = new Properties();
            p.load(is);
            value = p.getProperty(key);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {is.close();} catch (IOException e) {}
        }
        return value;
    }

    /**
     * 프로퍼티 파일에 사용자 값을 넣는다.
     */
    public void putPropertie(Map paramMap)
            throws FileNotFoundException, IOException {
        // 프로퍼티 파일 경로 key
        String propertiesKey = "properties.file.path";
        Properties proper = null;
        FileOutputStream output = null;
        try {
            String comment = (String) paramMap.get("properties.comment");

            if (!paramMap.containsKey(propertiesKey)) {
                paramMap.put(propertiesKey, propertiesFilePath);
            }
            output = new FileOutputStream((String) paramMap.get(propertiesKey));

            proper = new Properties();
            proper.putAll(paramMap);
            proper.store(output, comment);
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("properties 파일을 찾을수 없습니다.");
        } catch (IOException ioe) {
            throw new IOException("putPropertie Exception!", ioe);
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        PropertiesManager propertiesManager = new PropertiesManager("./PropertiesManager.properties");

        Map map = new HashMap();
        map.put( "id", "Hello"); // Test
        map.put( "age", "21");
        map.put( "name", "World");

        propertiesManager.putPropertie(map);

        System.out.println(propertiesManager.getKey("name"));
    }
}