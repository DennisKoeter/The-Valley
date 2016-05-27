package com.groep33.client;

import com.groep33.server.Constants;
import com.groep33.server.GlobalServerImpl;
import com.groep33.shared.GlobalServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class ClientApplication {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",Constants.PORT_NUMBER);
        GlobalServer server = (GlobalServer) registry.lookup(Constants.BINDING_NAME);
        server.registerLobby(new LobbyImpl(new ClientImpl("test"), "lobby"));
        server.getLobbies().forEach(lobby -> {
            try {
                System.out.println(lobby.getLobbyName());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}