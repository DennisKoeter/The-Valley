package com.groeps33.gui;

import com.groep33.interfaces.IChatMessage;
import com.groep33.interfaces.IClient;
import com.groep33.interfaces.IGameServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class Client extends UnicastRemoteObject implements IClient {

    private String username;
    private boolean ready;
    private Lobby lobby;

    public Client(String username) throws RemoteException {
        this.username = username;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public void receiveMessage(IChatMessage message, IClient sender) throws RemoteException {

    }

    @Override
    public void receiveNewPlayer(IClient player) throws RemoteException {

    }

    @Override
    public void receiveReadyNotification(boolean ready, IClient sender) throws RemoteException {

    }

    @Override
    public void setReady(boolean ready) throws RemoteException {

    }

    @Override
    public void createGameClient(IGameServer gameServer) throws RemoteException {

    }

    @Override
    public void kick() throws RemoteException {

    }

    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
    }
}
