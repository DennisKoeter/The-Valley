package com.groeps33.valley;

import com.badlogic.gdx.Game;
import com.groeps33.server.application.Constants;
import com.groeps33.server.shared.UserAccount;
import com.groeps33.server.shared.game.IGameClient;
import com.groeps33.server.shared.game.IGameServer;
import com.groeps33.server.shared.game.IGameAdministration;
import com.groeps33.valley.screens.IntroScreen;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class TheValleyGame extends Game {

    private static IGameAdministration gameAdministration;
    private final UserAccount userAccount;
    private IGameServer gameServer;

    public TheValleyGame(UserAccount userAccount, String gameUuid) {
        this.userAccount = userAccount;
        try {
            getGameAdministration();
            gameServer = gameAdministration.getGameById(gameUuid);
            if (gameServer == null) {
                JOptionPane.showMessageDialog(null, "Session is not found.", "Game could not be started!", JOptionPane.WARNING_MESSAGE);
                dispose();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public IGameServer getGameServer() {
        return gameServer;
    }

    @Override
    public void create() {
        setScreen(new IntroScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    public IGameAdministration getGameAdministration() {
        if (gameAdministration == null) {
            try {
                Registry registry = LocateRegistry.getRegistry(Constants.RMI_IP, Constants.PORT_NUMBER);
                gameAdministration = (IGameAdministration) registry.lookup(Constants.GAME_ADMIN_NAME);
                return gameAdministration;
            } catch (RemoteException | NotBoundException e) {
                e.printStackTrace();
            }
        }

        return gameAdministration;
    }
}