package com.groep33.shared;

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
