package org.iotlabs.webserver;

import org.iotlabs.webserver.blueprints.AbstractBluePrint;
import org.iotlabs.webserver.blueprints.MessageBluePrint;
import org.iotlabs.webserver.blueprints.UsersBluePrint;

public class SparkProxy {
    private static class Holder {
        private static final SparkProxy SPARK_PROXY = new SparkProxy();
    }

    public static SparkProxy getInstance() {
        return Holder.SPARK_PROXY;
    }

    private SparkProxy() {
        new SparkInit();
    }

    public void run() {
        registerRouters();
    }

    /**
     * register default setted routers.
     */
    private void registerRouters() {
        new UsersBluePrint().register();
        new MessageBluePrint().register();
    }

    /**
     * register blueprint implementations
     * @param bluePrint router blueprint
     */
    public void register(AbstractBluePrint bluePrint) {
        bluePrint.register();
    }
}
