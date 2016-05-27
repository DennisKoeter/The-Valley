package com.groeps33.gui.createlobby;
import com.groep33.client.ClientImpl;
import com.groep33.client.LobbyImpl;
import com.groeps33.gui.Constants;
import com.groeps33.gui.ValleyFX;
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

        createLobby(name, maxPlayers, password);
        ValleyFX.changeScene(getClass().getResource(Constants.MENU_PATH));
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource("../menu/menu.fxml"));
    }

    private void createLobby(String name, int maxPlayers, String password){
        //TODO save lobby in global server
        try {
            ValleyFX.getGlobalServer().registerLobby(new LobbyImpl(new ClientImpl("henk"), name));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
