package org.iotlabs.webserver.httpexceptions;

public class BaseHttpException {
    private int code;
    private String body;

    BaseHttpException(int code, String body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public String getBody() {
        return body;
    }
}
