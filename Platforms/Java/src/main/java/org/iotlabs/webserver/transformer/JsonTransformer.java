package org.iotlabs.webserver.transformer;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
    private static final Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}
