package com.groeps33.valley;

import com.badlogic.gdx.Game;
import com.groeps33.valley.net.GameClient;
import com.groeps33.valley.net.GameServer;
import com.groeps33.valley.net.UserAccount;
import com.groeps33.valley.screens.CharacterScreen;
import com.groeps33.valley.screens.GameScreen;

import javax.swing.*;
import java.io.IOException;

public class TheValleyGame extends Game {

    private final UserAccount userAccount;
    private final boolean isHost;
    private final String host;
    private GameServer gameServer;
    private GameClient gameClient;

    public TheValleyGame(UserAccount userAccount, String host, boolean isHost) {
        this.userAccount = userAccount;
        this.host = host;
        this.isHost = isHost;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    @Override
    public void create() {
        setScreen(new CharacterScreen(this)); //new IntroScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameClient.disconnect(userAccount.getUsername());
    }

    public void setupNetworking(GameScreen gameScreen) {
        try {
            if (isHost) {
                gameServer = new GameServer();
            }
            gameClient = new GameClient(gameScreen, host);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public GameClient getGameClient() {
        return gameClient;
    }
}