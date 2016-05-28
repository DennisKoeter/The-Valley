package com.groeps33.gui.screens.createlobby;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.ILobby;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
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
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.LOBBY_PATH), createdLobby);
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
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
