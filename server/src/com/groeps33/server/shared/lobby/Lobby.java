package com.groeps33.server.shared.lobby;

import com.groeps33.server.shared.UserAccount;
import com.groeps33.server.shared.game.Game;
import com.groeps33.server.shared.game.GameAdministration;
import com.groeps33.server.shared.game.IGame;
import com.groeps33.server.shared.lobby.exceptions.AlreadyJoinedException;
import com.groeps33.server.shared.lobby.exceptions.LobbyFullException;
import com.groeps33.server.shared.lobby.exceptions.UncorrectPasswordException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Edwin
 *         Created on 5/28/2016
 */
public class Lobby extends UnicastRemoteObject implements ILobby {
    private final List<UserAccount> userAccountList;
    private final UserAccount creator;
    private final String lobbyName;
    private final String password;
    private final int maximumPlayers;
    private final int id;

    public Lobby(UserAccount creator, String lobbyName, String password, int maximumPlayers, int id) throws RemoteException {
        this.creator = creator;
        this.lobbyName = lobbyName;
        this.password = password;
        this.maximumPlayers = maximumPlayers;
        this.id = id;
        userAccountList = new ArrayList<>();
        userAccountList.add(creator);
    }

    @Override
    public void registerUser(UserAccount userAccount, String password) throws RemoteException, AlreadyJoinedException, UncorrectPasswordException, LobbyFullException {
        if (hasPassword() && !this.password.equals(password)) {
            throw new UncorrectPasswordException(lobbyName, password);
        }

        if (isFull()) {
            throw new LobbyFullException();
        }

        if (userAccountList.contains(userAccount)) {
            throw new AlreadyJoinedException(userAccount);
        }

        userAccountList.add(userAccount);
    }

    @Override
    public void removeUser(UserAccount userAccount) throws RemoteException {
//        if (userAccount.equals(creator)) {
//            hostDisconnected();
//        }
        userAccountList.remove(userAccount);

        if (userAccountList.size() == 0) {
            LobbyAdministration.get().removeLobby(this);
        }
    }

//    @Override
//    public void broadcastMessage(IChatMessage message, UserAccount sender) throws RemoteException {
//        for (UserAccount userAccount : userAccountList) {
//            if (!userAccount.equals(sender)) {
//                userAccount.receiveMessage(message, sender);
//            }
//        }
//    }

//    @Override
//    public void broadcastReady(boolean ready, IClient sender) throws RemoteException {
//        for (IClient client : userAccountList) {
//            if (!client.equals(sender)) {
//                client.receiveReadyNotification(ready, sender);
//            }
//        }
//    }

    @Override
    public String getLobbyName() throws RemoteException {
        return lobbyName;
    }

    @Override
    public List<UserAccount> getRegisteredUserAccounts() throws RemoteException {
        return Collections.unmodifiableList(userAccountList);
    }

    @Override
    public void startGame() throws RemoteException {
        String gameUuid = GameAdministration.get().registerGame();
        //todo push gameUuid to all clients
    }

    @Override
    public String toString() {
        String returnString;
        try {
            returnString = this.getLobbyName();
            for (UserAccount c : this.userAccountList) {
                returnString += c.getUsername();
            }
            return returnString;
        } catch (RemoteException e) {
            returnString = "error";
            e.printStackTrace();
        }
        return returnString;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public int getMaximumPlayers() {
        return maximumPlayers;
    }

    @Override
    public int getPlayerCount() {
        return userAccountList.size();
    }

    @Override
    public boolean isFull() throws RemoteException {
        return userAccountList.size() >= maximumPlayers;
    }

    @Override
    public boolean hasPassword() throws RemoteException {
        return password != null && !password.isEmpty();
    }
}
