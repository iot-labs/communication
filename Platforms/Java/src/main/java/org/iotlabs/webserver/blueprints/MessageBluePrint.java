package org.iotlabs.webserver.blueprints;

import static spark.Spark.*;

public class MessageBluePrint extends AbstractBluePrint {

    public MessageBluePrint() {
        super("message");
    }

    @Override
    public void register() {
        get(getEndPoint("test"), (req, res) -> "Hello World - from test");
        get(getEndPoint("test2"), (req, res) -> "Hello World - from test2");
    }
}
