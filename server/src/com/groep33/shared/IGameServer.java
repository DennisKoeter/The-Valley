package com.groep33.shared;

import java.rmi.RemoteException;
import java.util.List;

/**
 *
 *
 * @author Bram Hoendervangers
 */
public interface IGameServer {

    void registerClient(IGameClient client) throws RemoteException;

    void start() throws RemoteException;

    List<IGameClient> getClients();

    //void onPlayerMoved(...)

    //void onCollision(..)
}
