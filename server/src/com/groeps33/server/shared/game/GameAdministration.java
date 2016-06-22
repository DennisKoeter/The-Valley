package com.groeps33.server.shared.game;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

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
    public IGameServer getGameForHost(String host) throws RemoteException {
        for (IGameServer game : games) {
            if (game.getHost().equals(host)) {
                return game;
            }
        }

        System.out.println("Session not found: "  + host);
        return null;
    }

    @Override
    public List<IGameServer> getGames() throws RemoteException {
        return Collections.unmodifiableList(games);
    }

    @Override
    public IGameServer registerGame(String host) throws RemoteException {
        IGameServer game = new GameServer(host);
        System.out.println(game.getHost());
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
