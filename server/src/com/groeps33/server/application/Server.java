package com.groeps33.server.application;

import com.groeps33.server.shared.game.GameAdministration;
import com.groeps33.server.shared.lobby.LobbyAdministration;

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

        System.out.printf("Binding lobby administration to name: '%s'\n", Constants.LOBBY_ADMIN_NAME);
        registry.rebind(Constants.LOBBY_ADMIN_NAME, LobbyAdministration.get());

        System.out.printf("Binding game administration to name: '%s'\n", Constants.GAME_ADMIN_NAME);
        registry.rebind(Constants.GAME_ADMIN_NAME, GameAdministration.get());
    }
}
