package org.iotlabs.webserver.utils;

import org.iotlabs.models.general.User;
import org.iotlabs.webserver.literals.ContentType;
import org.iotlabs.webserver.literals.Header;
import spark.Request;

public class Checker {

    public static boolean isRequestingHtml(Request request) {
        String accept = request.headers(Header.ACCEPT);
        return accept != null
                && accept.contains(ContentType.HTML);
    }

    public static User authenticate(String username, String passwd) {
        User user = Dummy.userMap.getOrDefault(username, null);
        if (user == null) {
            return null;
        }
        if (user.getPasswd().equals(passwd)) {
            return user;
        }
        return null;
    }




}
