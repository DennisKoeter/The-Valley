package com.groeps33.gui.settings;

import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.*;
import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {
    @FXML
    Checkbox audioFxCheckbox;

    @FXML
    Checkbox audioMusicCheckbox;

    @FXML
    private void back() throws IOException {
        //TODO implement checkbox selection
        ValleyFX.changeScene(getClass().getResource("../menu/menu.fxml"));
    }
}
