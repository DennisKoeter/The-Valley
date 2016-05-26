package com.groep33.shared;

import com.groep33.client.LobbyImpl;

import java.rmi.RemoteException;

/**
 * Created by Bram on 25-5-2016.\
 *
 * Represents the client, before the client is ingame.
 * @author Bram Hoendervangers
 */
public interface Client {

    void receiveMessage(ChatMessage message, Client sender) throws RemoteException;

  //  IPlayer getLocalPlayer() throws RemoteException;

    void receiveNewPlayer(Client player) throws RemoteException;

    void receiveReadyNotification(boolean ready, Client sender) throws RemoteException;

    void setReady(boolean ready) throws RemoteException;

    void createGameClient(GameServer gameServer) throws RemoteException;

    void kick() throws RemoteException;
}
