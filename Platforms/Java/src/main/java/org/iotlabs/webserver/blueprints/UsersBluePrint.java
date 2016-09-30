package org.iotlabs.webserver.blueprints;

import static spark.Spark.get;

public class UsersBluePrint extends AbstractBluePrint {

    public UsersBluePrint() {
        super("users");
    }

    @Override
    public void register() {
        get(getEndPoint("hello"), (req, res) -> "Hello World");
    }
}
