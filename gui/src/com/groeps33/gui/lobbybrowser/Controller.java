package com.groeps33.gui.lobbybrowser;

import com.groep33.client.LobbyImpl;
import com.groep33.shared.Lobby;
import com.groeps33.gui.ValleyFX;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

    @FXML
    ListView<Lobby> lobbiesListView;


    @FXML
    protected void initialize() {
        System.out.println("initialise");
        getLobbies();
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource("../menu/menu.fxml"));
    }

    @FXML
    private void confirm() throws RemoteException {
        Lobby selectedLobby = lobbiesListView.getSelectionModel().getSelectedItem();
        selectedLobby.registerClient(ValleyFX.getClient());
    }

    private void getLobbies(){
        try {
            lobbiesListView.itemsProperty().setValue(FXCollections.observableArrayList( ValleyFX.getGlobalServer().getLobbies()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
