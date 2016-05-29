package com.groeps33.server.shared.game;

import com.groeps33.server.shared.UserAccount;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IGameServer {
    String getUUID();

    List<IGameClient> getConnectedClients() throws RemoteException;

    IGameClient registerClient(UserAccount userAccount) throws RemoteException;

    void removeClient(IGameClient gameClient) throws RemoteException;

    boolean isRunning() throws RemoteException;
}
