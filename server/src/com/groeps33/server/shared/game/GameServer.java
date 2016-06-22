package com.groeps33.server.shared.game;

import com.groeps33.server.shared.UserAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Bram on 5/29/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameServer extends UnicastRemoteObject implements IGameServer {

    private final String host;
    private boolean running;
    private boolean someoneConnected;
    private final List<IGameClient> gameClients;
    private int idCounter = 0;
    private List<IMonsterClient> monsters;

    public GameServer(String host) throws RemoteException {
        this.host = host;
        running = true;
        gameClients = new ArrayList<>();
        monsters = new ArrayList<>();

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (someoneConnected) {
                    try {
                        synchronized (gameClients) {
                            long currentT = System.currentTimeMillis();
                            Iterator<IGameClient> it = gameClients.iterator();
                            while (it.hasNext()) {
                                GameClient gameClient = (GameClient) it.next();
                                if (currentT - gameClient.getLastUpdateTime() > 1000) {
                                    System.out.println("Player timeout: " + gameClient.getUserAccount().getEmail());
                                    System.out.println("Disconnecting..");
                                    it.remove();
                                }
                            }

                            if (gameClients.size() == 0) {
                                System.out.println("No players left for game: " + host);
                                GameAdministration.get().removeGame(GameServer.this);
                                running = false;
                                cancel();
                            }
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 200);
    }

    @Override
    public String getHost() throws RemoteException {
        return host;
    }

    @Override
    public List<IGameClient> getConnectedClients() throws RemoteException {
        return Collections.unmodifiableList(gameClients);
    }

    @Override
    public List<IMonsterClient> getMonsters() throws RemoteException {
        return Collections.unmodifiableList(monsters);
    }

    @Override
    public IGameClient registerClient(UserAccount userAccount) throws RemoteException {
        System.out.println("Register client: " + userAccount.getEmail());
        IGameClient gameClient = new GameClient(idCounter++, userAccount);
        gameClients.add(gameClient);
        someoneConnected = true;
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

    @Override
    public boolean hadSomeoneConnected() throws RemoteException {
        return someoneConnected;
    }
}
