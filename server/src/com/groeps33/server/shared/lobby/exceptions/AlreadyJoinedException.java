package com.groeps33.server.shared.lobby.exceptions;

import com.groeps33.server.shared.UserAccount;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class AlreadyJoinedException extends Exception {
    public AlreadyJoinedException(UserAccount userAccount) {
        super("Can't register client. Client: " + userAccount.getEmail() + " has already joined this lobby.");
    }
}
