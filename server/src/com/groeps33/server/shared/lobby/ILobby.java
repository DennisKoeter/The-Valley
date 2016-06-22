package com.groeps33.server.shared.lobby;

import com.groeps33.server.shared.UserAccount;
import com.groeps33.server.shared.game.IGameServer;
import com.groeps33.server.shared.lobby.exceptions.AlreadyJoinedException;
import com.groeps33.server.shared.lobby.exceptions.InsufficientPermissionsException;
import com.groeps33.server.shared.lobby.exceptions.LobbyFullException;
import com.groeps33.server.shared.lobby.exceptions.IncorrectPasswordException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface ILobby extends Remote {

    void registerUser(UserAccount userAccount, String password) throws RemoteException, AlreadyJoinedException, IncorrectPasswordException, LobbyFullException;

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

    void startGame(UserAccount userAccount, String host) throws RemoteException, InsufficientPermissionsException;

    UserAccount getHost() throws RemoteException;

    void pulse(UserAccount userAccount) throws RemoteException;

    /**
     * Checks if the game has started yet, if so, it will return the UUID of the game.
     * @return the unique identifier of the started game, <b>null</b> if not started yet.
     * @throws RemoteException
     */
    IGameServer getGameServer() throws RemoteException;

    List<Message> getMessages() throws RemoteException;

    void registerMessage(UserAccount userAccount, String message) throws RemoteException;
}
