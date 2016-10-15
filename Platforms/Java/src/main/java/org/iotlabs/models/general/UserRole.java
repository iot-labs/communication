package org.iotlabs.models.general;

import com.google.gson.annotations.SerializedName;

public enum UserRole {
    @SerializedName("ADMIN")
    ADMIN("ADMIN"),
    @SerializedName("NORMAL")
    NORMAL("NORMAL"),
    @SerializedName("VIEWER")
    VIEWER("VIEWER");

    private final String role;
    UserRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }

    public static UserRole fromStr(String str) {
        if (ADMIN.toString().equals(str)) {
            return ADMIN;
        } else if (NORMAL.toString().equals(str)) {
            return NORMAL;
        } else if (VIEWER.toString().equals(str)) {
            return VIEWER;
        } else {
            throw new IllegalStateException("no such enumeration.");
        }
    }
}
