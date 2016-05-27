package com.groeps33.interfaces;

import java.rmi.RemoteException;

/**
 * Represents the client when the game has started
 *
 * @author Bram Hoendervangers
 */
public interface IGameClient {

    //updateLocation(...)

    //updateCollision(...)


    void disconnect() throws RemoteException;
}
