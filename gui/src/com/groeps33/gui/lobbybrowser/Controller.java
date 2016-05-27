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

    private String getId(String lobbyString) {
        StringBuilder result = new StringBuilder();

        String name = lobbyString.substring(0, 3);
        result.append(name);
        System.out.println("First chars of lobby name were " + name);

        String number = lobbyString.substring(3, 6);
        result.append(number);
        System.out.println("3 numbers were " + number);

        return result.toString();
    }

    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {
        String id = getId(lobbiesListView.getSelectionModel().getSelectedItem());
        Lobby selectedLobby = ValleyFX.getGlobalServer().getLobbyById(id);
        selectedLobby.registerClient(ValleyFX.getClient());
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
