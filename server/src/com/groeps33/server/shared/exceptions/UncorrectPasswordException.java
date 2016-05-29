package com.groeps33.server.shared.exceptions;

import java.rmi.RemoteException;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class UncorrectPasswordException extends Exception {
    public UncorrectPasswordException(String lobbyName, String password) {
        super("Password: " + password + " is not correct for lobby: " + lobbyName + ".");
    }
}
