package com.groeps33.gui.screens.createlobby;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.lobby.ILobby;
import com.groeps33.server.shared.lobby.exceptions.LobbyNameAlreadyExistsException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        ValleyFX.changeScene(url, createdLobby);
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
    }

    private ILobby createLobby(String name, int maxPlayers, String password){
        try {
            return ValleyFX.getLobbyAdministration().registerLobby(ValleyFX.getUserAccount(), name, password, maxPlayers);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (LobbyNameAlreadyExistsException e) {
            ValleyFX.showMessageBox(Alert.AlertType.ERROR, "Name already exists", "There is already a lobby with the name: " + name + "!\nPlease choose a different name.");
        }
        return null;
    }
}
