package com.groeps33.valley.net;

import java.io.Serializable;

public class UserAccount implements Serializable {
    private final String username;
    private final String email;

    public UserAccount(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;

        UserAccount that = (UserAccount) o;
        return getUsername().equals(that.getUsername()) && getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        int result = getUsername().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
