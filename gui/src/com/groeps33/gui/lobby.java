package com.groeps33.gui;

import java.rmi.Remote;

/**
 * Created by Dennis on 26/05/16
 * Package: com.groeps33.gui
 */
public class Lobby implements Remote{
    private String name;
    private int maxPlayers;
    private String password;

    public String getName() {
        return name;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public String getPassword() {
        return password;
    }

    public Lobby(String name, int maxPlayers, String password) {
        this.name = name;
        this.maxPlayers = maxPlayers;
        this.password = password;
    }
}
