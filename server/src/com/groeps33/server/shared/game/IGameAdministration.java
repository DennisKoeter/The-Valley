package com.groeps33.server.shared.game;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IGameAdministration {

    IGame getGameById(String uuid) throws RemoteException;

    List<IGame> getGames() throws RemoteException;

    String registerGame() throws RemoteException;

    void removeGame() throws RemoteException;
}
