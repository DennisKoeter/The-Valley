package com.groeps33.gui.login;

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
    TextField passwordField;

    @FXML
    private void login() throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if(checkLogin(username, password)) ValleyFX.changeScene(getClass().getResource("../menu/menu.fxml"));
    }

    @FXML
    private void register() throws IOException {
        ValleyFX.changeScene(getClass().getResource("../register/settings.fxml"));
    }

    @FXML
    private void forgot() throws IOException {
        //TODO implement password forgotten functionality
        ValleyFX.changeScene(getClass().getResource("../forgotten/forgotten.fxml"));
    }

    private boolean checkLogin(String username, String password){
        //TODO implement login check
        return true;
    }
}
