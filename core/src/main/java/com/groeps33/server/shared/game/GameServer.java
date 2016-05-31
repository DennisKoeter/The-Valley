package com.groeps33.server.shared.game;

import com.groeps33.server.shared.UserAccount;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameServer implements IGameServer, Serializable {

    private final String uuid;
    private boolean running;
    private List<IGameClient> gameClients;

    public GameServer(String uuid) {
        this.uuid = uuid;
        running = true;
        gameClients = new ArrayList<>();
    }

    @Override
    public String getUUID() {
        return uuid;
    }

    @Override
    public List<IGameClient> getConnectedClients() {
        return Collections.unmodifiableList(gameClients);
    }

    @Override
    public IGameClient registerClient(UserAccount userAccount) {
        IGameClient gameClient = new GameClient(userAccount);
        gameClients.add(gameClient);
        return gameClient;
    }

    @Override
    public void removeClient(IGameClient gameClient) throws RemoteException {
        gameClients.remove(gameClient);

        System.out.println("Removed client");
        if (gameClients.isEmpty()) {
            //idereen disconnected, sluit game
            GameAdministration.get().removeGame(this);
            running = false;
        }
    }

    @Override
    public boolean isRunning() throws RemoteException {
        return running;
    }
}
