package com.groep33.server;

import com.groep33.shared.GlobalServer;
import com.groep33.shared.Lobby;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class GlobalServerImpl extends UnicastRemoteObject implements GlobalServer {

    private final List<Lobby> lobbyList;

    protected GlobalServerImpl() throws RemoteException {
        this.lobbyList = new ArrayList<>();
    }


    @Override
    public void registerLobby(Lobby lobby) throws RemoteException {
        lobbyList.add(lobby);
    }

    @Override
    public void removeLobby(Lobby lobby) throws RemoteException {
        lobbyList.remove(lobby);
    }

    @Override
    public List<Lobby> getLobbies() throws RemoteException {
        return Collections.unmodifiableList(lobbyList);
    }

    @Override
    public boolean checkLoginDetails(String username, String password) throws RemoteException {
        return true; //TODO connect with database
    }


}
