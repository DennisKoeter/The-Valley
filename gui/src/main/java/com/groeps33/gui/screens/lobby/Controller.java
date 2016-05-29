package com.groeps33.gui.screens.lobby;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.lobby.ILobby;
import com.groeps33.server.shared.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Dennis on 25/05/16.
 */
public class Controller {

    @FXML
    ListView<String> userAccountsListView;

    private ILobby thisLobby;

    public void init(ILobby l) throws RemoteException {
        if (l != null) {
            thisLobby = l;
            getUserAccounts(thisLobby);
        } else {
            System.out.println("Lobby is null in setlobby");
        }
    }

    private void getUserAccounts(ILobby l) throws RemoteException {
        if (l != null) {
            List<String> userAccountNames = new ArrayList<>();
            for (UserAccount c : l.getRegisteredUserAccounts()) {
                if (c == null) userAccountNames.add("user was null");
                else userAccountNames.add(c.getUsername());
            }
            ObservableList<String> userAccountNamesObservable = FXCollections.observableList(userAccountNames);
            userAccountsListView.setItems(userAccountNamesObservable);
        } else {
            System.out.println("Lobby is null in getuseraccounts");
        }
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

        thisLobby.removeUser(ValleyFX.getUserAccount());
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {

    }
}
