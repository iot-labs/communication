package org.iotlabs.util;

import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for string stuff.
 */
public class StringUtils {
    public static final Charset CHARSET = Charset.forName("UTF-8");

    private static final Logger logger = Logger.getLogger(StringUtils.class);
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

    /**
     * create sha-256 hash of origin string
     * @param origin string to create hash
     * @return sha-256 hashed string
     */
    public static String sha256(String origin) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(origin.getBytes(CHARSET));
            return DatatypeConverter.printHexBinary(messageDigest.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        return null;
    }
}

