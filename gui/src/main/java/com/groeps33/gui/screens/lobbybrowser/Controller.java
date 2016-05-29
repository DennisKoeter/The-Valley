package com.groeps33.gui.screens.lobbybrowser;

 import com.groeps33.gui.application.Constants;
 import com.groeps33.gui.application.ValleyFX;
 import javafx.beans.value.ChangeListener;
 import javafx.beans.value.ObservableValue;
 import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
 import javafx.geometry.Insets;
 import javafx.scene.control.Button;
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
    Button confirmButton;

    @FXML
    protected void initialize() {
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

        lobbiesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {

                confirmButton.setDisable(newValue.isFull());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });

        getLobbies();
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
        try {
            List<ILobby> lobbies = ValleyFX.getLobbyAdministration().getLobbies();
            ObservableList<ILobby> lobbiesObservable = FXCollections.observableList(lobbies);
            lobbiesListView.setItems(lobbiesObservable);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
