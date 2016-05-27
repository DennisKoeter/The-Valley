package com.groep33.client;

import com.groep33.shared.IChatMessage;
import com.groep33.shared.Client;
import com.groep33.shared.GameServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class ClientImpl extends UnicastRemoteObject implements Client {

    private String username;
    private boolean ready;
    private LobbyImpl lobby;

    public ClientImpl(String username) throws RemoteException {
        this.username = username;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public void receiveMessage(IChatMessage message, Client sender) throws RemoteException {

    }

    @Override
    public void receiveNewPlayer(Client player) throws RemoteException {

    }

    @Override
    public void receiveReadyNotification(boolean ready, Client sender) throws RemoteException {

    }

    @Override
    public void setReady(boolean ready) throws RemoteException {

    }

    @Override
    public void createGameClient(GameServer gameServer) throws RemoteException {

    }

    @Override
    public void kick() throws RemoteException {

    }

    public void setLobby(LobbyImpl lobby) {
        this.lobby = lobby;
    }
}
