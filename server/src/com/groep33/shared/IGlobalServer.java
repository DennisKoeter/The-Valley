package com.groep33.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface IGlobalServer extends Remote {
    void registerLobby(Lobby lobby) throws RemoteException;

    void removeLobby(Lobby lobby) throws RemoteException;

    List<Lobby> getLobbies() throws RemoteException;

    Lobby getLobbyById(String id) throws RemoteException;

    boolean checkLoginDetails(String username, String password) throws RemoteException;
}
