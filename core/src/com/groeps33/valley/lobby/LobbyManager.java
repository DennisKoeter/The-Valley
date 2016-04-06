package com.groeps33.valley.lobby;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

/**
 * @author Edwin
 * Created on 4/6/2016
 */
public class LobbyManager {
    String name;
    Character creator;
    int playerAmount;
    String password;
    List<Lobby> lobbies;

    /**
     * Creates a new lobby and puts it in this class' list
     * @return returns the newly created lobby
     */
    public Lobby createLobby() {
        // todo: create a lobby, put it in this list and return the created c
        throw new NotImplementedException();
    }

    /**
     * Returns the lobby that matches the given name, if there match any
     * @param name the name of the lobby
     * @return the lobby that's been searched for
     */
    public Lobby findLobby(String name) {
        // todo: returns a lobby that's searched for in this class' list by the given name
        throw new NotImplementedException();
    }

}
