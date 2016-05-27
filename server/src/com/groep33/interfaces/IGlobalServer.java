package com.groep33.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface IGlobalServer extends Remote {
    void registerLobby(ILobby lobby) throws RemoteException;

    void removeLobby(ILobby lobby) throws RemoteException;

    List<ILobby> getLobbies() throws RemoteException;

    ILobby getLobbyById(String id) throws RemoteException;

    boolean checkLoginDetails(String username, String password) throws RemoteException;
}
