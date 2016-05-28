package com.groeps33.gui.screens.createlobby;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.ILobby;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    TextField nameField;

    @FXML
    TextField maxPlayersField;

    @FXML
    TextField passwordField;

    @FXML
    private void confirm() throws IOException {
        String name = nameField.getText();
        int maxPlayers = Integer.parseInt(maxPlayersField.getText());
        String password = passwordField.getText();

        ILobby createdLobby = createLobby(name, maxPlayers, password);
        URL url = ValleyFX.class.getResource(Constants.LOBBY_PATH);
        System.out.println("URL: " + url);
        ValleyFX.changeScene((url), createdLobby);
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.LOBBY_PATH));
    }

    private ILobby createLobby(String name, int maxPlayers, String password){
        //TODO save lobby in global server
        try {
            return ValleyFX.createLobby(name, maxPlayers, password);
        } catch (RemoteException e) {
            e.printStackTrace();
            System.out.println("Error occurred in createLobby");
            return null;
        }
    }
}
