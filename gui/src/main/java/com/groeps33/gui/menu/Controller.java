package com.groeps33.gui.menu;

import com.groeps33.gui.Constants;
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
    private void createGame() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.CREATEGAME_PATH));
    }

    @FXML
    private void joinGame() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.JOINGAME_PATH));
    }

    @FXML
    private void settings() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.SETTINGS_PATH));
    }

    @FXML
    private void highscores() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.HIGHSCORES_PATH));
    }

    @FXML
    private void doubloonShop(){
        throw new NotImplementedException();
    }

    @FXML
    private void logOut() throws IOException {
<<<<<<< HEAD:gui/src/main/java/com/groeps33/gui/menu/Controller.java
        if(checkLogout()) ValleyFX.changeScene(getClass().getResource("../com.groeps33.gui.login/com.groeps33.gui.login.fxml"));
=======
        if(checkLogout()) ValleyFX.changeScene(getClass().getResource(Constants.LOGIN_PATH));
>>>>>>> b37460c00874906bf75501cc4bb79801003f1327:gui/src/com/groeps33/gui/menu/Controller.java
    }

    private boolean checkLogout() {
        //TODO implement logout functionality
        return true;
    }
}
