package org.iotlabs.webserver.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory of Defaults.
 */
public class DefaultFactory {
    private static Map<String, Object> htmlDefMap;
    static {
        htmlDefMap = new HashMap<>();
        htmlDefMap.put("title", "IoTLabs");
    }

    /**
     * get IoTLabs page's default mustache template value map
     * @return default map's for mustache template
     */
    public static Map<String, Object> getHtmlDefaults() {
        return new HashMap<>(htmlDefMap);
    }
}
