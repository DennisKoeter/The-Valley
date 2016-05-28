package com.groeps33.server.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Bram on 25-5-2016.
 */
public interface ILobbyAdministration extends Remote {
    ILobby registerLobby(UserAccount host, String name) throws RemoteException;

    void removeLobby(ILobby lobby) throws RemoteException;

    List<ILobby> getLobbies() throws RemoteException;

    ILobby getLobbyById(String id) throws RemoteException;

    UserAccount login(String username, String password) throws RemoteException;

    UserAccount register(String username, String password, String email) throws RemoteException;
}
