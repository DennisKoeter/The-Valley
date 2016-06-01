package com.groeps33.server.shared.lobby;

import com.groeps33.server.shared.UserAccount;

import java.io.Serializable;

/**
 * Created by Bram on 6/1/2016.
 *
 * @author Bram Hoendervangers
 */
public class Message implements Serializable {

    private final UserAccount sender;
    private final String message;

    public Message(UserAccount sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public UserAccount getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }
}
