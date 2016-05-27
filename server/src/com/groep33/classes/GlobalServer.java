package com.groep33.classes;

import com.groep33.interfaces.IGlobalServer;
import com.groep33.interfaces.ILobby;

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
public class GlobalServer extends UnicastRemoteObject implements IGlobalServer {

    private final List<ILobby> lobbyList;

    protected GlobalServer() throws RemoteException {
        this.lobbyList = new ArrayList<>();
    }


    @Override
    public void registerLobby(ILobby lobby) throws RemoteException {
        System.out.println("registered lobby");
        lobbyList.add(lobby);
        System.out.println(lobby.getLobbyName());
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
    public boolean checkLoginDetails(String username, String password) throws RemoteException {
        return true; //TODO connect with database
    }

    @Override
    public ILobby getLobbyById(String id) throws RemoteException {
        for(ILobby l : lobbyList){
            if(l.getId().equals(id)) return l;
        }
        return null;
    }
}
