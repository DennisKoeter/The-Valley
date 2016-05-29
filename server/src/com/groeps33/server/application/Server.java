package com.groeps33.server.application;

import com.groeps33.server.shared.ILobbyAdministration;
import com.groeps33.server.shared.LobbyAdministration;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Bram on 25-5-2016.
 */
public class Server {

    public static void main(String[] args) throws RemoteException {
        System.out.println("Creating registry at: " + Constants.PORT_NUMBER);
        Registry registry = LocateRegistry.createRegistry(Constants.PORT_NUMBER);

        System.out.printf("Binding server to name: '%s'\n", Constants.BINDING_NAME);
        registry.rebind(Constants.BINDING_NAME, LobbyAdministration.get());
    }
}
