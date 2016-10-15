package org.iotlabs.models.general;

import com.google.gson.annotations.SerializedName;
import org.iotlabs.models.BaseModel;

public class User extends BaseModel {
    @SerializedName("user")
    private String username;
    private transient String passwd;
    @SerializedName("role")
    private UserRole role;

    /**
     * UserRole constructor
     * role=ADMIN
     * @param username unique user name
     * @param passwd user password from sha256
     */
    public User(String username, String passwd) {
        this(username, passwd, UserRole.ADMIN);
    }

    /**
     * UserRole constructor
     * @param username unique user name
     * @param passwd user password from sha256
     * @param role user role
     */
    public User(String username, String passwd, UserRole role) {
        this.username = username;
        this.passwd = passwd;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswd() {
        return passwd;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof User)) {
            return false;
        }
        User otherUser = (User) other;
        return this.username.equals(otherUser.username)
                && this.passwd.equals(otherUser.passwd)
                && this.role.equals(otherUser.role);
    }

    @Override
    public int hashCode() {
        return username.concat(passwd).concat(role.toString()).hashCode();
    }
}
