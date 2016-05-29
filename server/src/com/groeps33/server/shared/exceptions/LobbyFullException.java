package com.groeps33.server.shared.exceptions;

import java.rmi.RemoteException;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class LobbyFullException extends Exception {
    public LobbyFullException() {
        super("Can't register new client. Lobby is already full.");
    }
}
