package com.groeps33.server.shared.game;

import com.groeps33.server.shared.UserAccount;

import java.rmi.RemoteException;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public interface IGameClient {
    UserAccount getUserAccount() throws RemoteException;
}
