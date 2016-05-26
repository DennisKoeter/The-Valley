package com.groeps33.gui.lobbybrowser;

import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

//    @FXML
//    ListView<>

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource("../menu/menu.fxml"));
    }

    @FXML
    private void confirm(){
        throw new NotImplementedException();
    }

    private void getLobbies(){
        throw new NotImplementedException();
    }
}
