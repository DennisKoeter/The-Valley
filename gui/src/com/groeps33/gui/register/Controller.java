package com.groeps33.gui.register;

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

    private void register(String username, String email, String password) throws IOException {
        //TODO implement registration
        ValleyFX.changeScene(getClass().getResource("../login/login.fxml"));
    }
}
