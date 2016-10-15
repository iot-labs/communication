package org.iotlabs.webserver.httpexceptions;

public class Http4xx extends BaseHttpException {

    public Http4xx(int code, String body) {
        super(code, body);
    }

}
