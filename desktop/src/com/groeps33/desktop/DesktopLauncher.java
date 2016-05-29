package com.groeps33.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.groeps33.server.shared.UserAccount;
import com.groeps33.valley.TheValleyGame;

class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
       // config.width = 640 * 2;
       // config.height = 480 * 2;

        new LwjglApplication(new TheValleyGame(new UserAccount(arg[0], arg[1]), arg[2]), config);
    }
}
