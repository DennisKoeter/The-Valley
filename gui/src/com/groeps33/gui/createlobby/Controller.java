package com.groeps33.gui.createlobby;

import com.groeps33.gui.Lobby;
import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

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
    private void confirm(){
        String name = nameField.getText();
        int maxPlayers = Integer.parseInt(maxPlayersField.getText());
        String password = passwordField.getText();

        createLobby(name, maxPlayers, password);
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource("../menu/menu.fxml"));
    }

    private void createLobby(String name, int maxPlayers, String password){
        Lobby lobby = new Lobby(name, maxPlayers, password);
        //TODO save lobby in global server
    }
}
