package com.groep33.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface Lobby extends Remote {

    void registerClient(Client client) throws RemoteException;

    void removeClient(Client client) throws RemoteException;

    void broadcastMessage(ChatMessage message, Client sender) throws RemoteException;

    void broadcastReady(boolean ready, Client sender) throws RemoteException;

    String getLobbyName() throws RemoteException;

    List<Client> getRegisteredClients() throws RemoteException;

    void startGame() throws RemoteException;

    void hostDisconnected() throws RemoteException;
}
