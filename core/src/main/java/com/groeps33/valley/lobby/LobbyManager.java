package com.groeps33.valley.lobby;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import com.groeps33.valley.entity.Character;

import java.util.List;

/**
 * @author Edwin
 * Created on 4/6/2016
 */
class LobbyManager {
    private List<Lobby> lobbies;

    /**
     * Creates a new lobby and puts it in this class' list
     * @return returns the newly created lobby
     */
    public Lobby createLobby(int id, String name, int playerAmount, String password, Character creator) {
        Lobby lobby = new Lobby(id, name, playerAmount, password, creator);
        this.lobbies.add(lobby);
        return lobby;
    }

    /**
     * Gets all lobbies this manager has references to
     * @return a list of lobbies
     */
    public List<Lobby> getLobbies(){ return this.lobbies; }

    /**
     * Returns the lobby that matches the given name, if there match any
     * @param name the name of the lobby
     * @return the lobby that's been searched for
     */
    public Lobby findLobby(String name) {
        // todo: returns a lobby that's searched for in this class' list by the given name
        throw new NotImplementedException();
    }

    /**
     * Closes the lobby that matches the given name
     * @param name the name of the lobby
     */
    public void closeLobby(String name) {
        throw new NotImplementedException();
    }
}
