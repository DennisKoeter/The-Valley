package com.groeps33.server.shared.game;

import com.groeps33.server.shared.lobby.LobbyAdministration;

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
    private final List<IGame> games;

    private GameAdministration() throws RemoteException {
        games = new ArrayList<>();
    }

    @Override
    public IGame getGameById(String uuid) throws RemoteException {
        for (IGame game : games) {
            if (game.getUUID().equals(uuid)) {
                return game;
            }
        }
        return null;
    }

    @Override
    public List<IGame> getGames() throws RemoteException {
        return Collections.unmodifiableList(games);
    }

    @Override
    public IGame registerGame() throws RemoteException {
        final String uuid = UUID.randomUUID().toString();
        IGame game = new Game(uuid);
        games.add(game);
        return game;
    }

    @Override
    public void removeGame() throws RemoteException {

    }


    public static GameAdministration get() throws RemoteException {
        return instance == null ? (instance = new GameAdministration()) : instance;

    }
}
