package com.groeps33.server.shared;

import com.groeps33.server.shared.exceptions.AlreadyJoinedException;
import com.groeps33.server.shared.exceptions.LobbyFullException;
import com.groeps33.server.shared.exceptions.UncorrectPasswordException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface ILobby extends Remote {

    void registerClient(UserAccount userAccount, String password) throws RemoteException, AlreadyJoinedException, UncorrectPasswordException, LobbyFullException;

    void removeClient(UserAccount userAccount) throws RemoteException;

//    void broadcastMessage(IChatMessage message, UserAccount sender) throws RemoteException;
//
//    void broadcastReady(boolean ready, UserAccount sender) throws RemoteException;

    String getLobbyName() throws RemoteException;

    List<UserAccount> getRegisteredUserAccounts() throws RemoteException;

//    void startGame() throws RemoteException;
//
//    void hostDisconnected() throws RemoteException;

    int getId() throws RemoteException;

    int getMaximumPlayers() throws RemoteException;

    int getPlayerCount() throws RemoteException;

    boolean isFull() throws RemoteException;

    boolean hasPassword() throws RemoteException;
}
