package com.groeps33.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.groeps33.server.application.Constants;
import com.groeps33.server.shared.UserAccount;
import com.groeps33.server.shared.game.IGameAdministration;
import com.groeps33.valley.TheValleyGame;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
       // config.width = 640 * 2;
       // config.height = 480 * 2;

        if (arg.length == 0) {
            //for debugging
            try {
                Registry registry = LocateRegistry.getRegistry(Constants.RMI_IP, Constants.PORT_NUMBER);
                IGameAdministration gameAdministration = (IGameAdministration) registry.lookup(Constants.GAME_ADMIN_NAME);
                new LwjglApplication(new TheValleyGame(new UserAccount("henk", "henk"), gameAdministration.registerGame().getUUID()), config);
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(Arrays.toString(arg));
            new LwjglApplication(new TheValleyGame(new UserAccount(arg[1], arg[2]), arg[0]), config);
        }
    }
}
