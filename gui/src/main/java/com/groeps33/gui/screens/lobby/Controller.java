package com.groeps33.gui.screens.lobby;

import com.groeps33.gui.application.Constants;
import com.groeps33.gui.application.ValleyFX;
import com.groeps33.server.shared.ILobby;
import com.groeps33.server.shared.UserAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

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
        thisLobby.removeClient(ValleyFX.getUserAccount());
        ValleyFX.changeScene(ValleyFX.class.getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {

    }
}
