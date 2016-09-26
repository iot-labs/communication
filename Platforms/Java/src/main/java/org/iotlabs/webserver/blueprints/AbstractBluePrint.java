package org.iotlabs.webserver.blueprints;

/**
 * Python flask's blueprint like abstract class
 */
public abstract class AbstractBluePrint {

    /**
     * blueprint name prefix
     */
    private String blueprintPrefix;

    /**
     * create blueprint object
     * @param blueprintPrefix blueprint name prefix to set
     */
    AbstractBluePrint(String blueprintPrefix) {
        if (!blueprintPrefix.startsWith("/")) {
            blueprintPrefix = "/" + blueprintPrefix;
        }
        if (!blueprintPrefix.endsWith("/")) {
            blueprintPrefix += "/";
        }
        this.blueprintPrefix = blueprintPrefix + "%s";
    }

    /**
     * Get endpoint with blueprint prefix
     * @param endPoint endpoint
     * @return {blueprint_prefix}/endPoint
     */
    String getEndPoint(String endPoint) {
        if (endPoint.startsWith("/")) {
            endPoint = endPoint.substring(1, endPoint.length());
        }
        return String.format(blueprintPrefix, endPoint);
    }

    public abstract void register();
}
