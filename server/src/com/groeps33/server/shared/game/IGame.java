package com.groeps33.server.shared.game;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IGame {
    String getUUID();

    List<IGameClient> getConnectedClients() throws RemoteException;

    IGameClient registerClient() throws RemoteException;

    void removeClient() throws RemoteException;
}
