package org.iotlabs.util;

import org.apache.log4j.Logger;

import java.io.Closeable;
import java.io.IOException;

/**
 * Utility class for IO stuff.
 *
 */
public class IOUtils {

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
                Logger.getLogger(IOUtils.class).info("Close quite.", e);
            }
        }
    }
}
