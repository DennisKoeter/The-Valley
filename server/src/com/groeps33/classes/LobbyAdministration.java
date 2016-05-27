package com.groeps33.classes;

import com.groeps33.interfaces.ILobbyAdministration;
import com.groeps33.interfaces.ILobby;

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
public class LobbyAdministration extends UnicastRemoteObject implements ILobbyAdministration {

    private final List<ILobby> lobbyList;
    private Database database;


    protected LobbyAdministration() throws RemoteException {
        this.lobbyList = new ArrayList<>();
        database = new Database();
    }

    @Override
    public void registerLobby(ILobby lobby) throws RemoteException {
        System.out.println("registered lobby");
        lobbyList.add(lobby);
//        System.out.println(lobby.getLobbyName());
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
    public ILobby getLobbyById(String id) throws RemoteException {
        for (ILobby l : lobbyList) {
//            if (l.getId().equals(id)) return l;
        }
        return null;
    }

    @Override
    public UserAccount login(String username, String password) throws RemoteException {
        return null;
        //TODO check login details and return useraccount object
    }

    @Override
    public UserAccount register(String username, String email, String password) throws RemoteException {
        return database.register(username, email, password);
    }
}
