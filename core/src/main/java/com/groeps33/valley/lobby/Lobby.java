package com.groeps33.valley.lobby;

import com.groeps33.valley.entity.Character;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Edwin
 * Created on 4/6/2016
 */
public class Lobby {
    private int id;
    private String name;
    private int playerAmount;
    private String password;
    private Character creator;
    private List<Character> characters;

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

    public List<Character> getCharacters(){ return this.characters; }

    /**
     * creates a new lobby
     * @param id the lobby id
     * @param name the name of the lobby
     * @param playerAmount the amount of players allowed
     * @param password the password, if none is set it will be an empty string like: ""
     * @param creator the creator of the lobby
     */
    public Lobby(int id, String name, int playerAmount, String password, Character creator) {
        this.id = id;
        this.name = name;
        this.playerAmount = playerAmount;
        if(password == null) password = "";
        else this.password = password;
        this.creator = creator;
        this.characters = new ArrayList<Character>();
    }

    public boolean addCharacter(Character chr, String passwd) {
        if(this.password == passwd) {
            characters.add(chr);
            return true;
        }
        return false;
    }
}
