package com.groeps33.gui.screens.lobby;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.game.IGameServer;
import com.groeps33.server.shared.lobby.ILobby;
import com.groeps33.server.shared.UserAccount;
import com.groeps33.server.shared.lobby.exceptions.InsufficientPermissionsException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

    @FXML
    Button startButton;

    @FXML
    Label lobbyNameLabel;

    @FXML
    Label playerCountLabel;

    @FXML
    ListView<String> userAccountsListView;

    private ILobby thisLobby;
    private TimerTask updateTask;

    public void init(ILobby l) throws RemoteException {
        thisLobby = l;
        lobbyNameLabel.setText(thisLobby.getLobbyName());
        startButton.setDisable(!thisLobby.getHost().equals(ValleyFX.getUserAccount()));

        //disable selection
        userAccountsListView.setMouseTransparent(true);
        userAccountsListView.setFocusTraversable(false);

        updateTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        if (l.getPlayerCount() == 0) {
                            cancel();
                        }
                        thisLobby.pulse(ValleyFX.getUserAccount());
                        updatePlayerCounts();
                        getUserAccounts(thisLobby);
                        IGameServer game = l.getGameServer();
                        if (game != null) {
                            if (!game.isRunning()) {
                                switchToStartButton();
                            } else {
                                if (game.hadSomeoneConnected()) {
                                    switchToJoinButton();
                                } else {
                                    ValleyFX.startGame(game.getUUID());
                                }
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                });

            }
        };

        new Timer().schedule(updateTask, 0, 500);
    }

    private void switchToStartButton() {
        startButton.setText("Start");
    }

    private void switchToJoinButton() {
        startButton.setText("Join");
        startButton.setDisable(false);
    }

    private void updatePlayerCounts() throws RemoteException {
        playerCountLabel.setText("Players: " + thisLobby.getPlayerCount() + "/" + thisLobby.getMaximumPlayers());
    }

    private void getUserAccounts(ILobby l) throws RemoteException {
        UserAccount host = l.getHost();
        List<String> userAccountNames;
        userAccountNames = l.getRegisteredUserAccounts().stream()
                .map(acc -> (acc.equals(host) ? "Host: " : "Player: ") + acc.getUsername()).collect(Collectors.toList());
        ObservableList<String> userAccountNamesObservable = FXCollections.observableList(userAccountNames);
        userAccountsListView.setItems(userAccountNamesObservable);
        startButton.setDisable(!thisLobby.getHost().equals(ValleyFX.getUserAccount()));
    }


    @FXML
    private void back() throws IOException {
        if (thisLobby.getPlayerCount() == 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Do you want to leave this lobby?");
            alert.setContentText("This lobby will be deleted if you leave, since you are the only person in it.");

            Optional<ButtonType> result = alert.showAndWait();
            if (!result.isPresent() || result.get() != ButtonType.OK) {
                return;
            }
        }

        updateTask.cancel();
        thisLobby.removeUser(ValleyFX.getUserAccount());
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {
        IGameServer gameServer = thisLobby.getGameServer();
        if (gameServer == null || !gameServer.isRunning()) {
            try {
                thisLobby.startGame(ValleyFX.getUserAccount());
            } catch (InsufficientPermissionsException e) {
                //should never get here, since the start button will be disabled for non-hosts.
                ValleyFX.showMessageBox(Alert.AlertType.WARNING, "Insufficient permissions", "Only the host can start a game");
            }
        } else {
            ValleyFX.startGame(gameServer.getUUID());
        }
    }
}
