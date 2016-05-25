package com.groeps33.gui.menu;

import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import sun.java2d.pipe.ValidatePipe;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    private void createGame(){
        throw new NotImplementedException();
    }

    @FXML
    private void joinGame(){
        throw new NotImplementedException();
    }

    @FXML
    private void settings() throws IOException {
        ValleyFX.changeScene(getClass().getResource("../settings/settings.fxml"));
    }

    @FXML
    private void highscores() throws IOException {
        ValleyFX.changeScene(getClass().getResource("../highscores/highscores.fxml"));
    }

    @FXML
    private void doubloonShop(){
        throw new NotImplementedException();
    }

    @FXML
    private void logOut() throws IOException {
        if(checkLogout()) ValleyFX.changeScene(getClass().getResource("../login/login.fxml"));
    }

    private boolean checkLogout() {
        //TODO implement logout functionality
        return true;
    }
}
