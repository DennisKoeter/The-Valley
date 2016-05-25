package com.groeps33.gui.creategame;

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
    TextField name;

    @FXML
    TextField maxPlayers;

    @FXML
    TextField password;

    @FXML
    private void confirm(){
        throw new NotImplementedException();
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource("start.menu.fxml"));
    }
}
