package com.groeps33.gui.lobby;

import com.groep33.shared.IClient;
import com.groep33.shared.Lobby;
import com.groeps33.gui.Constants;
import com.groeps33.gui.ValleyFX;
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
    ListView<String> clientsListView;

    private Lobby thisLobby;

    @FXML
    private void initialize(){
        try {
            getClients(thisLobby);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setLobby(Lobby l) {
        thisLobby = l;
    }

    private void getClients(Lobby l) throws RemoteException{
        List<String> clientNames = new ArrayList();
        for(IClient c : l.getRegisteredClients()){
            clientNames.add(c.getUsername());
        }
        ObservableList<String> clientNamesObservable = FXCollections.observableList(clientNames);
        clientsListView.setItems(clientNamesObservable);
    }


    @FXML
    private void back() throws IOException {
        ValleyFX.changeScene(getClass().getResource(Constants.MENU_PATH));
    }

    @FXML
    private void confirm() throws RemoteException {

    }
}
