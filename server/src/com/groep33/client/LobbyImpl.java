package com.groep33.client;

import com.groep33.shared.ChatMessage;
import com.groep33.shared.Client;
import com.groep33.shared.GameServer;
import com.groep33.shared.Lobby;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class LobbyImpl extends UnicastRemoteObject implements Lobby {

    private final List<Client> clientList;
    private final Client creator;
    private final String lobbyName;
    private final String id;

    public LobbyImpl(ClientImpl creator, String lobbyName) throws RemoteException {
        this.creator = creator;
        creator.setLobby(this);
        this.lobbyName = lobbyName;
        this.id = generateId();
        clientList = new ArrayList<>();
        clientList.add(creator);
    }

    @Override
    public void registerClient(Client client) throws RemoteException {
        System.out.println("Registered client");
        clientList.add(client);
    }

    @Override
    public void removeClient(Client client) throws RemoteException {
        clientList.remove(client);
    }

    @Override
    public void broadcastMessage(ChatMessage message, Client sender) throws RemoteException {
        for (Client client : clientList) {
            if (!client.equals(sender)) {
                client.receiveMessage(message, sender);
            }
        }
    }

    @Override
    public void broadcastReady(boolean ready, Client sender) throws RemoteException {
        for (Client client : clientList) {
            if (!client.equals(sender)) {
                client.receiveReadyNotification(ready, sender);
            }
        }
    }

    @Override
    public String getLobbyName() throws RemoteException {
        return lobbyName;
    }

    @Override
    public List<Client> getRegisteredClients() throws RemoteException {
        return Collections.unmodifiableList(clientList);
    }

    @Override
    public void startGame() throws RemoteException {
        GameServer gameServer = new GameServerImpl();
        for (Client client : clientList) {
            client.createGameClient(gameServer);
        }
    }

    @Override
    public void hostDisconnected() throws RemoteException {
        for (Client client : clientList) {
            client.kick();
        }
    }

    @Override
    public String toString() {
        String returnString;
        try {
            returnString = this.getLobbyName();
            for(Client c : this.clientList){
                returnString += c.getUsername();
            }
            return returnString;
        } catch (RemoteException e) {
            returnString = "error";
            e.printStackTrace();
        }
        return returnString;
    }

    private String generateId(){
        Random random = new Random();
        String number = String.valueOf(random.nextInt(1000));
        String letters = lobbyName.substring(0, 2);
        return letters + number;
    }

    @Override
    public String getId(){
        return id;
    }
}
