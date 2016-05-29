package com.groeps33.server.shared.game;

import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class Game implements IGame {

    private final String uuid;

    public Game(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getUUID() {
        return uuid;
    }

    @Override
    public List<IGameClient> getConnectedClients() {
        return null;
    }

    @Override
    public IGameClient registerClient() {
        return null;
    }

    @Override
    public void removeClient() {

    }
}
