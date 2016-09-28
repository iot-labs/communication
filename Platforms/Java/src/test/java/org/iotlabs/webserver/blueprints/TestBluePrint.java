package org.iotlabs.webserver.blueprints;

import static spark.Spark.get;

public class TestBluePrint extends AbstractBluePrint  {

    public TestBluePrint() {
        super("test");
    }

    @Override
    public void register() {
        get(getEndPoint("/"), (req, res)  -> "test");
    }
}