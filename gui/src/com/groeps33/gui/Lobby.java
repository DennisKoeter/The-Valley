package com.groeps33.gui;

//import com.groep33.interfaces.IChatMessage;
//import com.groep33.interfaces.IClient;
//import com.groep33.interfaces.IGameServer;
//import com.groep33.interfaces.ILobby;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
//public class Lobby extends UnicastRemoteObject implements ILobby {
//
//    private final List<IClient> clientList;
//    private final IClient creator;
//    private final String lobbyName;
//    private final String id;
//
//    public Lobby(UserAccount creator, String lobbyName) throws RemoteException {
//        this.creator = creator;
//        creator.setLobby(this);
//        this.lobbyName = lobbyName;
//        this.id = generateId();
//        clientList = new ArrayList<>();
//        clientList.add(creator);
//    }
//
//    @Override
//    public void registerClient(IClient client) throws RemoteException {
//        System.out.println("Registered client");
//        clientList.add(client);
//    }
//
//    @Override
//    public void removeClient(IClient client) throws RemoteException {
//        clientList.remove(client);
//    }
//
//    @Override
//    public void broadcastMessage(IChatMessage message, IClient sender) throws RemoteException {
//        for (IClient client : clientList) {
//            if (!client.equals(sender)) {
//                client.receiveMessage(message, sender);
//            }
//        }
//    }
//
//    @Override
//    public void broadcastReady(boolean ready, IClient sender) throws RemoteException {
//        for (IClient client : clientList) {
//            if (!client.equals(sender)) {
//                client.receiveReadyNotification(ready, sender);
//            }
//        }
//    }
//
//    @Override
//    public String getLobbyName() throws RemoteException {
//        return lobbyName;
//    }
//
//    @Override
//    public List<IClient> getRegisteredClients() throws RemoteException {
//        return Collections.unmodifiableList(clientList);
//    }
//
//    @Override
//    public void startGame() throws RemoteException {
//        IGameServer gameServer = new GameServer();
//        for (IClient client : clientList) {
//            client.createGameClient(gameServer);
//        }
//    }
//
//    @Override
//    public void hostDisconnected() throws RemoteException {
//        for (IClient client : clientList) {
//            client.kick();
//        }
//    }
//
//    @Override
//    public String toString() {
//        String returnString;
//        try {
//            returnString = this.getLobbyName();
//            for (IClient c : this.clientList) {
//                returnString += c.getUsername();
//            }
//            return returnString;
//        } catch (RemoteException e) {
//            returnString = "error";
//            e.printStackTrace();
//        }
//        return returnString;
//    }
//
//    private String generateId() {
//        int number = new Random().nextInt(1000);
//
//        String numberString;
//        if (number < 10) {
//            numberString = "00" + number;
//            System.out.println("Random number was 0-9");
//        } else if (number < 100) {
//            numberString = "0" + number;
//            System.out.println("Random number was 10-99");
//        } else {
//            numberString = String.valueOf(number);
//            System.out.println("Random number was 100-999");
//        }
//
//        String letters = lobbyName.substring(0, 3);
//        return letters + numberString;
//    }
//
//    @Override
//    public String getId() {
//        return id;
//    }
//}
