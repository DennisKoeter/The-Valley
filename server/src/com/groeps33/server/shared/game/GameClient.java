package com.groeps33.server.shared.game;

import com.groeps33.server.shared.UserAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameClient extends UnicastRemoteObject implements IGameClient {
    private final int id;
    private final UserAccount userAccount;
    private float x;
    private float y;
    private byte direction;
    private long lastUpdateTime;

    public GameClient(int id, UserAccount userAccount) throws RemoteException {
        this.id = id;
        this.userAccount = userAccount;
        lastUpdateTime = System.currentTimeMillis();
    }

    @Override
    public UserAccount getUserAccount() throws RemoteException {
        return userAccount;
    }

    @Override
    public float getX() throws RemoteException {
        return x;
    }

    @Override
    public float getY() throws RemoteException {
        return y;
    }

    @Override
    public void update(float x, float y, byte direction) throws RemoteException {
        lastUpdateTime = System.currentTimeMillis();
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

    @Override
    public byte getDirection() throws RemoteException {
        return direction;
    }

    @Override
    public void setDirection(byte direction) throws RemoteException {
        this.direction = direction;
    }

    @Override
    public int getId() throws RemoteException {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameClient)) return false;

        GameClient that = (GameClient) o;

        return userAccount != null ? userAccount.equals(that.userAccount) : that.userAccount == null;

    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public int hashCode() {
        return userAccount != null ? userAccount.hashCode() : 0;
    }
}
