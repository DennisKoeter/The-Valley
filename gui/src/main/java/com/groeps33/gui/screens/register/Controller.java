package com.groeps33.gui.screens.register;

 import com.groeps33.gui.application.ValleyFX;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import com.groeps33.server.shared.UserAccount;

import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    TextField usernameField;

    @FXML
    TextField emailField;

    @FXML
    TextField passwordField;

    @FXML
    private void confirm() throws IOException {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        register(username, email, password);
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource("../menu/menu.fxml"));
    }

    private void register(String username, String email, String password) throws IOException {
        UserAccount userAccount = ValleyFX.getLobbyAdministration().register(username, email, password);
        ValleyFX.setUserAccount(userAccount);
        ValleyFX.changeScene(ValleyFX.class.getResource("../com.groeps33.gui.login/com.groeps33.gui.login.fxml"));
    }
}
