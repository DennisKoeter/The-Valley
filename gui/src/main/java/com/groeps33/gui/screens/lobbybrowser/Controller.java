package com.groeps33.gui.screens.lobbybrowser;

 import com.groeps33.gui.application.Constants;
 import com.groeps33.gui.application.ValleyFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import com.groeps33.server.shared.ILobby;

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

    private void onLobbySelected(ILobby lobby) {
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
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {
        String id = getId(lobbiesListView.getSelectionModel().getSelectedItem());
        ILobby selectedLobby = ValleyFX.getLobbyAdministration().getLobbyById(id);
        selectedLobby.registerClient(ValleyFX.getUserAccount());

        try {
            ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH), selectedLobby);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLobbies() {
//        System.out.println("Retrieving lobbies now");
        try {
            List<ILobby> lobbies = ValleyFX.getLobbyAdministration().getLobbies();
            List<String> lobbyNames = new ArrayList<>();
            for (ILobby l : lobbies) {
                lobbyNames.add(String.format("%s, %s, %s clients", l.getId(), l.getLobbyName(), l.getRegisteredClients().size()));
            }

            ObservableList<String> lobbiesObservable = FXCollections.observableList(lobbyNames);

            lobbiesListView.setItems(lobbiesObservable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
