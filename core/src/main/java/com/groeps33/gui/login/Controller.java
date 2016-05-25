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
    TextField username;

    @FXML
    TextField password;

    @FXML
    private void login(){
        throw new NotImplementedException();
    }

    @FXML
    private void register() throws IOException {
        ValleyFX.changeScene(getClass().getResource("register.register.fxml"));
    }
}
