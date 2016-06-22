package com.groeps33.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.groeps33.valley.TheValleyGame;
import com.groeps33.valley.net.UserAccount;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.Random;

class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 640;//* 2;
        config.height = 480;//* 2;
        if (arg.length == 0) {
            //for debugging
//            try {
//                Registry registry = LocateRegistry.getRegistry(Constants.RMI_IP, Constants.PORT_NUMBER);
//                IGameAdministration gameAdministration = (IGameAdministration) registry.lookup(Constants.GAME_ADMIN_NAME);
//                new LwjglApplication(new TheValleyGame(new UserAccount("henk", "henk"), gameAdministration.registerGame().getUUID()), config);
//            } catch (RemoteException | NotBoundException e) {
//                e.printStackTrace();
//            }
            new LwjglApplication(new TheValleyGame(new UserAccount("henk" + new Random(System.nanoTime()).nextInt(100), "henk"), "127.0.0.1"), config);
        } else {
            System.out.println(Arrays.toString(arg));
            new LwjglApplication(new TheValleyGame(new UserAccount(arg[1], arg[2]), arg[0]), config);
        }
    }
}
