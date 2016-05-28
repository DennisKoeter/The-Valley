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
    private final String id;

    public Lobby(UserAccount creator, String lobbyName) throws RemoteException {
        this.creator = creator;
        this.lobbyName = lobbyName;
        this.id = generateId();
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

    private String generateId() {
        int number = new Random().nextInt(1000);

        String numberString;
        if (number < 10) {
            numberString = "00" + number;
            System.out.println("Random number was 0-9");
        } else if (number < 100) {
            numberString = "0" + number;
            System.out.println("Random number was 10-99");
        } else {
            numberString = String.valueOf(number);
            System.out.println("Random number was 100-999");
        }

        String letters = lobbyName.substring(0, 3);
        return letters + numberString;
    }

    @Override
    public String getId() throws RemoteException {
        return id;
    }
}
