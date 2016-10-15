package org.iotlabs.webserver.blueprints;

import org.iotlabs.util.StringUtils;

/**
 * Python flask's blueprint like abstract class
 */
public abstract class AbstractBluePrint {

    /**
     * blueprint name prefix
     */
    private String blueprintPrefix;
    private String blueprintPrefixFormatter;
    private String blueprintResourcePath;

    /**
     * create blueprint object
     * @param blueprintPrefix blueprint name prefix to set
     */
    public AbstractBluePrint(String blueprintPrefix) {
        if (!blueprintPrefix.startsWith("/")) {
            blueprintPrefix = "/" + blueprintPrefix;
        }
        if (blueprintPrefix.endsWith("/")) {
            blueprintPrefix = blueprintPrefix.substring(0, blueprintPrefix.length() - 1);
        }
        this.blueprintPrefix = blueprintPrefix;
        this.blueprintPrefixFormatter = blueprintPrefix + "/%s";
        this.blueprintResourcePath = "web/html/" + blueprintPrefix;
    }

    /**
     * Get endpoint with blueprint prefix
     * @param endPoint endpoint
     * @return {blueprint_prefix}/endPoint
     */
    public String getEndPoint(String endPoint) {

        if (StringUtils.isEmpty(endPoint)) {
            return blueprintPrefix;
        }

        if (endPoint.startsWith("/")) {
            endPoint = endPoint.substring(1, endPoint.length());
        }
        return String.format(blueprintPrefixFormatter, endPoint);
    }

    /**
     * Get template resource path for Mustache engine.
     * @return "web/html/{blue_print}"
     */
    public String getTemplatePath() {
        return blueprintResourcePath;
    }

    public abstract void register();
}
