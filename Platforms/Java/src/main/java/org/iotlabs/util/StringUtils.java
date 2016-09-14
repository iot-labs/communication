package org.iotlabs.util;

import java.nio.charset.Charset;

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
}
