package com.groeps33.gui.screens.lobbybrowser;

 import com.groeps33.gui.application.Constants;
 import com.groeps33.gui.application.ValleyFX;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
 import javafx.geometry.Insets;
 import javafx.scene.control.ListCell;
 import javafx.scene.control.ListView;
import com.groeps33.server.shared.ILobby;
 import javafx.scene.control.SingleSelectionModel;
 import javafx.scene.layout.Background;
 import javafx.scene.layout.BackgroundFill;
 import javafx.scene.layout.CornerRadii;
 import javafx.scene.paint.Color;
 import javafx.util.Callback;

 import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

    @FXML
    ListView<ILobby> lobbiesListView;

    @FXML
    protected void initialize() {
        //String.format("%d: %s, %d/%d", l.getId(), l.getLobbyName(), l.getRegisteredUserAccounts().size())

        lobbiesListView.setCellFactory(param -> new ListCell<ILobby>(){
            @Override
            protected void updateItem(ILobby l, boolean empty) {
                super.updateItem(l, empty);
                if (l != null) {
                    try {
                        setText(String.format("%d: %s, %d/%d players", l.getId(), l.getLobbyName(), l.getPlayerCount(), l.getMaximumPlayers()));
                        if (l.isFull()) {
                            setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

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
        int id = lobbiesListView.getSelectionModel().getSelectedItem().getId();
        ILobby selectedLobby = ValleyFX.getLobbyAdministration().getLobbyById(id);
        selectedLobby.registerClient(ValleyFX.getUserAccount());
        try {
            ValleyFX.changeScene(ValleyFX.class.getResource(Constants.LOBBY_PATH), selectedLobby);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLobbies() {
//        System.out.println("Retrieving lobbies now");
        try {
            List<ILobby> lobbies = ValleyFX.getLobbyAdministration().getLobbies();
            List<String> lobbyNames = new ArrayList<>();


            ObservableList<ILobby> lobbiesObservable = FXCollections.observableList(lobbies);

            lobbiesListView.setItems(lobbiesObservable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
