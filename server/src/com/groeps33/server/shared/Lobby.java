package com.groeps33.server.shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author Edwin
 *         Created on 5/28/2016
 */
public class Lobby extends UnicastRemoteObject implements ILobby{
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
    public void registerClient(UserAccount userAccount) throws RemoteException {
        System.out.println("Registered client");
        userAccountList.add(userAccount);
    }

    @Override
    public void removeClient(UserAccount userAccount) throws RemoteException {
        userAccountList.remove(userAccount);
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

//    @Override
//    public void startGame() throws RemoteException {
//        IGameServer gameServer = new GameServer();
//        for (IClient client : userAccountList) {
//            client.createGameClient(gameServer);
//        }
//    }
//
//    @Override
//    public void hostDisconnected() throws RemoteException {
//        for (IClient client : userAccountList) {
//            client.kick();
//        }
//    }

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
}
