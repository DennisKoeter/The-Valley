package com.groep33.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface Lobby extends Remote {

    void registerClient(IClient client) throws RemoteException;

    void removeClient(IClient client) throws RemoteException;

    void broadcastMessage(IChatMessage message, IClient sender) throws RemoteException;

    void broadcastReady(boolean ready, IClient sender) throws RemoteException;

    String getLobbyName() throws RemoteException;

    List<IClient> getRegisteredClients() throws RemoteException;

    void startGame() throws RemoteException;

    void hostDisconnected() throws RemoteException;

    String getId() throws RemoteException;
}
