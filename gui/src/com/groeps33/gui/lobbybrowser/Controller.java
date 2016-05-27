package com.groeps33.gui.lobbybrowser;

import com.groep33.client.LobbyImpl;
import com.groep33.shared.Lobby;
import com.groeps33.gui.Constants;
import com.groeps33.gui.ValleyFX;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

    @FXML
    ListView<String> lobbiesListView;


    @FXML
    protected void initialize() {
        getLobbies();
    }

    private void onLobbySelected(Lobby lobby) {
        System.out.format("%s was selected", lobby);
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {
        //Lobby selectedLobby = lobbiesListView.getSelectionModel().getSelectedItem();
        //selectedLobby.registerClient(ValleyFX.getClient());
    }

    private void getLobbies() {
        try {
            List<Lobby> lobbies = ValleyFX.getGlobalServer().getLobbies();
            List<String> lobbyNames = new ArrayList<>();
            for (Lobby l : lobbies) {
                lobbyNames.add(String.format("%s, %s, %s clients", l.getId(), l.getLobbyName(), l.getRegisteredClients().size()));
            }

            ObservableList<String> lobbiesObservable = FXCollections.observableList(lobbyNames);

            lobbiesListView.setItems(lobbiesObservable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
