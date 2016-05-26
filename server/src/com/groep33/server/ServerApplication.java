package com.groep33.server;

import com.groep33.shared.GlobalServer;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Bram on 25-5-2016.
 */
public class ServerApplication {

    private static final int PORT_NUMBER = 1203;
    private static final String BINDING_NAME = "Server";

    public static void main(String[] args) throws RemoteException {
        System.out.println("Creating registry at: " + Constants.PORT_NUMBER);
        Registry registry = LocateRegistry.createRegistry(Constants.PORT_NUMBER);

        System.out.printf("Binding server to name: '%s'\n", Constants.BINDING_NAME);
        GlobalServer server = new GlobalServerImpl();
        registry.rebind(Constants.BINDING_NAME, server);
    }
}
