package com.groeps33.server.shared.game;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IGameAdministration {

    /**
     * find a specific game
     * @param uuid the id of the game
     * @return the game object
     * @throws RemoteException
     */
    IGame getGameById(String uuid) throws RemoteException;

    List<IGame> getGames() throws RemoteException;

    IGame registerGame() throws RemoteException;

    void removeGame() throws RemoteException;
}
