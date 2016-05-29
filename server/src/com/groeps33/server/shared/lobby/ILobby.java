package com.groeps33.server.shared.lobby;

import com.groeps33.server.shared.UserAccount;
import com.groeps33.server.shared.lobby.exceptions.AlreadyJoinedException;
import com.groeps33.server.shared.lobby.exceptions.LobbyFullException;
import com.groeps33.server.shared.lobby.exceptions.UncorrectPasswordException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface ILobby extends Remote {

    void registerUser(UserAccount userAccount, String password) throws RemoteException, AlreadyJoinedException, UncorrectPasswordException, LobbyFullException;

    void removeUser(UserAccount userAccount) throws RemoteException;

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

    void startGame() throws RemoteException;
}
