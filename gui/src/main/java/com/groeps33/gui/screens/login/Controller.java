package com.groeps33.gui.screens.login;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
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
        if(checkLogin(username, password)) ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
    }

    @FXML
    private void register() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.REGISTER_PATH))   ;
    }

    @FXML
    private void forgot() throws IOException {
        //TODO implement password forgotten functionality
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.FORGOTTEN_PATH));
    }

    private boolean checkLogin(String username, String password){
        //TODO implement com.groeps33.gui.login check
        return true;
    }
}
