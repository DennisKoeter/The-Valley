package com.groeps33.server.shared;

import com.groeps33.server.application.Database;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 *
 * @author Bram Hoendervangers
 */
public class LobbyAdministration extends UnicastRemoteObject implements ILobbyAdministration {

    private static LobbyAdministration instance;
    private final List<ILobby> lobbyList;
    private Database database;


    private LobbyAdministration() throws RemoteException {
        this.lobbyList = new ArrayList<>();
        database = new Database();
    }

    @Override
    public ILobby registerLobby(UserAccount userAccount, String name, String password, int maximumPlayers) throws RemoteException {
        ILobby lobby = new Lobby(userAccount, name, password, maximumPlayers, lobbyList.size());
        lobbyList.add(lobby);
        return lobby;
    }

    @Override
    public void removeLobby(ILobby lobby) throws RemoteException {
        lobbyList.remove(lobby);
    }

    @Override
    public List<ILobby> getLobbies() throws RemoteException {
        return Collections.unmodifiableList(lobbyList);
    }

    @Override
    public ILobby getLobbyById(int id) throws RemoteException {
        for (ILobby l : lobbyList) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    @Override
    public UserAccount login(String username, String password) throws RemoteException {
//        try {
            return new UserAccount("Henk", "henk@henk.nl");//database.login(username, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    @Override
    public UserAccount register(String username, String email, String password) throws RemoteException {
        try {
            return database.register(username, email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static LobbyAdministration get() {
        try {
            return instance == null ? (instance = new LobbyAdministration()) : instance;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }
}
