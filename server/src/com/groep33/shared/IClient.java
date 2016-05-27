package com.groep33.shared;

import java.rmi.RemoteException;

/**
 * Created by Bram on 25-5-2016.\
 *
 * Represents the client, before the client is ingame.
 * @author Bram Hoendervangers
 */
public interface IClient {

    void receiveMessage(IChatMessage message, IClient sender) throws RemoteException;

  //  IPlayer getLocalPlayer() throws RemoteException;

    void receiveNewPlayer(IClient player) throws RemoteException;

    void receiveReadyNotification(boolean ready, IClient sender) throws RemoteException;

    void setReady(boolean ready) throws RemoteException;

    void createGameClient(IGameServer gameServer) throws RemoteException;

    void kick() throws RemoteException;

    String getUsername();
}
