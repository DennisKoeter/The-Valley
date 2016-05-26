package com.groeps33.gui.lobbybrowser;

import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

<<<<<<< HEAD:gui/src/main/java/com/groeps33/gui/lobbybrowser/Controller.java
    @FXML
    protected void initialize() {
        System.out.println("initilaize");
    }
=======
//    @FXML
//    ListView<>
>>>>>>> b37460c00874906bf75501cc4bb79801003f1327:gui/src/com/groeps33/gui/lobbybrowser/Controller.java

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
