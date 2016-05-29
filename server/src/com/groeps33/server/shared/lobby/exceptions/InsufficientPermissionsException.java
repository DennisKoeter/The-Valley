package com.groeps33.server.shared.lobby.exceptions;

/**
 * Created by Bram on 29-5-2016.
 */
public class InsufficientPermissionsException extends Exception {
    public InsufficientPermissionsException(String message) {
        super(message);
    }
}
