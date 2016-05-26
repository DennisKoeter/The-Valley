package com.groep33.client;

import com.groep33.shared.GameClient;
import com.groep33.shared.GameServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class GameServerImpl extends UnicastRemoteObject implements GameServer {

    List<GameClient> clients = new ArrayList<>();

    protected GameServerImpl() throws RemoteException {
    }

    @Override
    public void registerClient(GameClient client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public void start() throws RemoteException {

    }

    @Override
    public List<GameClient> getClients() {
        return null;
    }
}
