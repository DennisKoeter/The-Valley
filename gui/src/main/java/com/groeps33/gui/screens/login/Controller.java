package com.groeps33.gui.screens.login;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.lobby.ILobbyAdministration;
import com.groeps33.server.shared.UserAccount;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    private void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        ILobbyAdministration admin = ValleyFX.getLobbyAdministration();
        UserAccount acc = admin.login(username, password);
        if (acc != null) {
            ValleyFX.setUserAccount(acc);
            ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
        } else {
            System.out.println("Login failed.");
        }
    }

    @FXML
    private void register() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.REGISTER_PATH));
    }

    @FXML
    private void forgot() throws IOException {
        //TODO implement password forgotten functionality
//        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.FORGOTTEN_PATH));
    }
}
