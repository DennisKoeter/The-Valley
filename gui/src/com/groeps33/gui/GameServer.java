package com.groeps33.gui;

import com.groep33.interfaces.IGameClient;
import com.groep33.interfaces.IGameServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class GameServer extends UnicastRemoteObject implements IGameServer {

    List<IGameClient> clients = new ArrayList<>();

    protected GameServer() throws RemoteException {
    }

    @Override
    public void registerClient(IGameClient client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public void start() throws RemoteException {

    }

    @Override
    public List<IGameClient> getClients() {
        return null;
    }
}
