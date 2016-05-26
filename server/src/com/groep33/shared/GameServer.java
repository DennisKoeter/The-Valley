package com.groep33.shared;

import java.rmi.RemoteException;
import java.util.List;

/**
 *
 *
 * @author Bram Hoendervangers
 */
public interface GameServer {

    void registerClient(GameClient client) throws RemoteException;

    void start() throws RemoteException;

    List<GameClient> getClients();

    //void onPlayerMoved(...)

    //void onCollision(..)
}
