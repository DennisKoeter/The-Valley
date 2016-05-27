package com.groeps33.classes;



import java.io.Serializable;

/**
 * @author Edwin
 *         Created on 5/27/2016
 */
public class UserAccount implements Serializable {
    private String username;
    private String email;

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
}
