package com.groeps33.gui.lobby;

import com.groep33.shared.Lobby;
import com.groeps33.gui.Constants;
import com.groeps33.gui.ValleyFX;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

    @FXML
    ListView<String> lobbiesListView;

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {

    }
}
