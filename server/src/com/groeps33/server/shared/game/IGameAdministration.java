package com.groeps33.server.shared.game;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IGameAdministration extends Remote {

    IGameServer getGameForHost(String uuid) throws RemoteException;

    List<IGameServer> getGames() throws RemoteException;

    IGameServer registerGame(String host) throws RemoteException;

    void removeGame(IGameServer game) throws RemoteException;

}
