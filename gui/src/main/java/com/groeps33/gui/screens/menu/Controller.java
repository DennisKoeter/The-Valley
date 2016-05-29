package com.groeps33.gui.screens.menu;

 import com.groeps33.gui.application.Constants;
 import com.groeps33.gui.application.ValleyFX;
import javafx.fxml.FXML;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    private void createGame() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.CREATEGAME_PATH));
    }

    @FXML
    private void joinGame() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.JOINGAME_PATH));
    }

    @FXML
    private void settings() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.SETTINGS_PATH));
    }

    @FXML
    private void highscores() throws IOException {
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.HIGHSCORES_PATH));
    }

    @FXML
    private void doubloonShop(){
        throw new NotImplementedException();
    }

    @FXML
    private void logOut() throws IOException {
        ValleyFX.setUserAccount(null);
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.LOGIN_PATH));
    }
}
