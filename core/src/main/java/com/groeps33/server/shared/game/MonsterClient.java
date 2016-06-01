package com.groeps33.server.shared.game;

import java.rmi.RemoteException;

/**
 * Created by Bram on 6/1/2016.
 *
 * @author Bram Hoendervangers
 */
public class MonsterClient implements IMonsterClient {
    private IGameClient target;

    @Override
    public IGameClient getTarget() throws RemoteException {
        return target;
    }

    public void setTarget(IGameClient target) {
        this.target = target;
    }
}
