package com.groeps33.server.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface ILobby extends Remote {

    void registerClient(UserAccount userAccount) throws RemoteException;

    void removeClient(UserAccount userAccount) throws RemoteException;

//    void broadcastMessage(IChatMessage message, UserAccount sender) throws RemoteException;
//
//    void broadcastReady(boolean ready, UserAccount sender) throws RemoteException;

    String getLobbyName() throws RemoteException;

    List<UserAccount> getRegisteredUserAccounts() throws RemoteException;

//    void startGame() throws RemoteException;
//
//    void hostDisconnected() throws RemoteException;

    String getId() throws RemoteException;
}
