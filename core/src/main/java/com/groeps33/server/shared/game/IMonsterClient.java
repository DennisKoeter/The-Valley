package com.groeps33.server.shared.game;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Bram on 6/1/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IMonsterClient extends Remote {

    IGameClient getTarget() throws RemoteException;
}
