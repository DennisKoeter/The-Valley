package com.groeps33.server.shared.lobby.exceptions;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class LobbyNameAlreadyExistsException extends Exception {
    public LobbyNameAlreadyExistsException(String lobbyName) {
        super("A lobby with the name: " + lobbyName + " already exists.");
    }
}
