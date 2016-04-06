package com.groeps33.valley.lobby;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by Edwin on 4/6/2016.
 */
public class Lobby {
    int id;
    String name;
    int playerAmount;
    String password;
    Character creator;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPlayerAmount() {
        return playerAmount;
    }

    public String getPassword() {
        return password;
    }

    public Character getCreator() {
        return creator;
    }

    public Lobby(int id, String name, int playerAmount, String password, Character creator) {
        this.id = id;
        this.name = name;
        this.playerAmount = playerAmount;
        this.password = password;
        this.creator = creator;
    }

    public boolean addCharacter(Character chr, String passwd) {
        //todo: add a character to the lobby, returning true if it's succeeded, false if it didn't. Check for the password integrity
        throw new NotImplementedException();
    }
}
