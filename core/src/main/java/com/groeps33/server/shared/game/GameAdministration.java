package com.groeps33.server.shared.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameAdministration extends UnicastRemoteObject implements IGameAdministration {
    private static GameAdministration instance;
    private final List<IGameServer> games;

    private GameAdministration() throws RemoteException {
        games = new ArrayList<>();
    }

    @Override
    public IGameServer getGameById(String uuid) throws RemoteException {
        for (IGameServer game : games) {
            if (game.getUUID().equals(uuid)) {
                return game;
            }
        }

        System.out.println("Session not found: "  + uuid);
        return null;
    }

    @Override
    public List<IGameServer> getGames() throws RemoteException {
        return Collections.unmodifiableList(games);
    }

    @Override
    public IGameServer registerGame() throws RemoteException {
        final String uuid = UUID.randomUUID().toString();
        IGameServer game = new GameServer(uuid);
        System.out.println(game.getUUID());
        games.add(game);
        return game;
    }

    @Override
    public void removeGame(IGameServer game) throws RemoteException {
        games.remove(game);
    }


    public static GameAdministration get() throws RemoteException {
        return instance == null ? (instance = new GameAdministration()) : instance;

    }
}
