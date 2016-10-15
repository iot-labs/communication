package org.iotlabs.webserver.utils;

import org.iotlabs.models.general.User;
import org.iotlabs.models.general.UserRole;

import java.util.HashMap;
import java.util.Map;

class Dummy {
    static User admin = new User("admin", "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08");
    static User normalUser = new User("toori67", "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08", UserRole.NORMAL);
    static Map<String, User> userMap = new HashMap<>();
    static {
        userMap.put(admin.getUsername(), admin);
        userMap.put(normalUser.getUsername(), normalUser);
    }
}
