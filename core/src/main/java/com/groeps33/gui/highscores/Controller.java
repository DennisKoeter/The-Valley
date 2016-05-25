package com.groeps33.gui.highscores;

import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene("start");
    }
}
