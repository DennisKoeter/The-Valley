package com.groeps33.server.shared.game;

import com.groeps33.server.shared.UserAccount;

import java.rmi.RemoteException;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameClient implements IGameClient {
    private final UserAccount userAccount;

    public GameClient(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public UserAccount getUserAccount() throws RemoteException {
        return userAccount;
    }
}
