package org.iotlabs.webserver.blueprints;


import spark.ResponseTransformer;
import spark.Route;
import spark.TemplateEngine;
import spark.TemplateViewRoute;

/**
 * Same endpoint with multiple http operation
 * e.g. `/auth/login` need POST for api and GET for html page.
 *
 */
public class Complex {

    public static Complex complex(String endpoint) {
        return new Complex(endpoint);
    }

    private final String endpoint;
    private Complex(String endpoint) {
        this.endpoint = endpoint;
    }

    public Complex get(Route route) {
        spark.Spark.get(endpoint, route);
        return this;
    }

    public Complex get(Route route, ResponseTransformer responseTransformer) {
        spark.Spark.get(endpoint, route, responseTransformer);
        return this;
    }

    public Complex get(TemplateViewRoute route, TemplateEngine templateEngine) {
        spark.Spark.get(endpoint, route, templateEngine);
        return this;
    }

    public Complex post(Route route) {
        spark.Spark.post(endpoint, route);
        return this;
    }

    public Complex post(Route route, ResponseTransformer responseTransformer) {
        spark.Spark.post(endpoint, route, responseTransformer);
        return this;
    }
}
