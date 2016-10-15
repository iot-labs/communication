package org.iotlabs.webserver.utils;

import org.iotlabs.webserver.httpexceptions.BaseHttpException;
import org.iotlabs.webserver.literals.ContentType;
import spark.Response;

public class ResponseSetter {
    public static void setErrorResponse(Response errorResponse, BaseHttpException e) {
        errorResponse.type(ContentType.HTML);
        errorResponse.status(e.getCode());
        errorResponse.body(e.getBody());
    }
}