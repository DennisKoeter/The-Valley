package com.groeps33.gui.screens.lobbybrowser;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.lobby.exceptions.AlreadyJoinedException;
import com.groeps33.server.shared.lobby.exceptions.LobbyFullException;
import com.groeps33.server.shared.lobby.exceptions.UncorrectPasswordException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import com.groeps33.server.shared.lobby.ILobby;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Optional;

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
        lobbiesListView.setCellFactory(param -> new ListCell<ILobby>() {
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
    private void confirm() {
        try {
            ILobby selectedLobby = lobbiesListView.getSelectionModel().getSelectedItem();
            if (selectedLobby == null) {
                ValleyFX.showMessageBox(Alert.AlertType.INFORMATION, "No lobby selected", "Please select a lobby first");
                return;
            }

            String password = null;
            if (selectedLobby.hasPassword()) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Enter password");
                dialog.setHeaderText("Lobby " + selectedLobby.getLobbyName() + " has a password.");
                dialog.setContentText("Please enter the password:");

                Optional<String> result = dialog.showAndWait();
                if (!result.isPresent()) {
                    return;
                }

                password = result.get();
            }

            selectedLobby.registerUser(ValleyFX.getUserAccount(), password);
            ValleyFX.changeScene(ValleyFX.class.getResource(Constants.LOBBY_PATH), selectedLobby);
        } catch (AlreadyJoinedException e) {
            ValleyFX.showMessageBox(Alert.AlertType.ERROR, "Already joined", "Your account is already in this lobby!");
        } catch (UncorrectPasswordException e) {
            ValleyFX.showMessageBox(Alert.AlertType.ERROR, "Password incorrect", "Please enter a correct password!");
        } catch (LobbyFullException | IOException e) {
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
