package org.iotlabs.util;

import java.nio.charset.Charset;

/**
 * Utility class for string stuff.
 */
public class StringUtils {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    /**
     * bytes array to UTF-8 encoded String.
     * @param bytes bytes array
     * @return utf-8 encoded string
     */
    public static String getString(byte[] bytes) {
        return new String(bytes, CHARSET);
    }

    /**
     * check string is null or empty
     * @param string string to evaluate
     * @return true if null or empty
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() <= 0;
    }

}

