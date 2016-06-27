package com.groeps33.valley.net;

import com.badlogic.gdx.math.Vector2;
import com.groeps33.valley.Constants;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.Monster;
import com.groeps33.valley.items.ItemSpawn;
import com.groeps33.valley.net.monsters.PathFinder;
import com.groeps33.valley.net.packet.*;
import com.groeps33.valley.util.Calculations;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Bram on 6/15/2016.
 * <p>
 * This is the server-side game server. It runs on the host player
 * and handles all game logic & packet related events.
 *
 * @author Bram Hoendervangers
 */

public class GameServer implements PacketListener {

    private static final Packet PLAYER_UPDATE_PACKET = new RequestPlayerUpdate();
    private static final Vector2 MONSTER_SPAWN = new Vector2(785, 1835);
    private final List<ClientConnection> connectedPlayers = new CopyOnWriteArrayList<>();
    //    private final List<Monster> monsters = new CopyOnWriteArrayList<>();
    private final PacketHandler handler;
    private final PathFinder pathFinder;

    private List<ItemSpawn> itemSpawnList = new ArrayList<>();
    ItemSpawn healthPotion;

    private Wave currentWave;

    public GameServer() throws IOException {
        DatagramSocket serverSocket = new DatagramSocket(Constants.SERVER_PORT);
        handler = new PacketHandler(serverSocket);
        handler.addListener(this);
        handler.start();

        pathFinder = new PathFinder();
        /**
         * Initialize all items that will spawn on the map
         */
        // create a hp pot with 60 second cooldown
//        healthPotion = new HealthPotion(Constants.HEALTH_POT_LOCATION_X, Constants.HEALTH_POT_LOCATION_Y, Constants.HEALTH_POT_COOLDOWN, 0, Constants.HEALTH_POT_AMOUNT_OF_HEAL);
        itemSpawnList.add(healthPotion);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //syncing hp (and other important stuff?)
                try {
                    for (ClientConnection client : connectedPlayers) {
                        syncClientWithConnectedPlayers(client);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, 0, Constants.SYNC_ALL_PLAYERS_INTERVAL);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                tick();
            }
        }, 0, Constants.TICK_INTERVAL);
    }

    private void tick() {
        if (currentWave == null) {
            currentWave = new Wave(1, System.currentTimeMillis() + 5000);
            broadcastPacket(new NewWave(1), null);
        }

        if (currentWave.isFinished()) {
            currentWave = new Wave(currentWave.getNumber() + 1, System.currentTimeMillis() + 5000);
            broadcastPacket(new NewWave(currentWave.getNumber()), null);
        }


        if (System.currentTimeMillis() > currentWave.getStartTime()) {
            currentWave.checkSpawn();
            List<Monster> deadMonsters = new ArrayList<>();
            for (Monster monster : currentWave.getMonsterList()) {
                if (monster.getCurrentHp() == 0) {
                    deadMonsters.add(monster);
                }
                ClientConnection closest = null;
                double dist = Double.MAX_VALUE;
                for (ClientConnection clientConnection : connectedPlayers) {
                    double t = Calculations.distance(clientConnection.getCharacter().getLocation(), monster.getLocation());
                    if (t < dist) {
                        dist = t;
                        closest = clientConnection;
                    }
                }

                if (closest != null) {

                    if (Calculations.distance(closest.getCharacter().getLocation(), monster.getLocation()) < 20 * 32 || System.currentTimeMillis() - monster.getLastTargetCheck() > 500) {
                        monster.setTarget(closest.getCharacter().getName());
                        pathFinder.generatePathForMonster(monster, closest.getCharacter());
//                broadcastPacket(new MonsterTargetUpdate(monster.getId(), monster.getTarget()), null);
                    }
                }

                monster.update(0.05f);
            }

            currentWave.getMonsterList().removeAll(deadMonsters);
        }


        int ids[] = new int[currentWave.getMonsterList().size()];
        float locsX[] = new float[ids.length];
        float locsY[] = new float[ids.length];

        for (int i = 0; i < ids.length; i++) {
            Monster monster = currentWave.getMonsterList().get(i);
            ids[i] = monster.getId();
            locsX[i] = monster.getLocation().x;
            locsY[i] = monster.getLocation().y;
        }

        broadcastPacket(new MonstersUpdatePacket(ids, locsX, locsY, currentWave.getNumber()), null);

        //for each item
        for (ItemSpawn item : itemSpawnList) {
//            if (!item.isTaken() && item.isReadyToSpawn()) {
//                item.setTaken(false);
//            }
        }
    }

    @Override
    public void onPingReceived(InetAddress address, int port) {
        try {
            handler.sendData(new byte[]{7}, address, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPacketReceived(Packet packet, InetAddress address, int port) {
        try {
            switch (packet.getType()) {
                case CONNECT:
                    ConnectPacket connectPacket = (ConnectPacket) packet;
                    System.out.println("server: connect -> " + connectPacket.getUsername());
                    Character character = new Character(-1, -1, connectPacket.getUsername(), connectPacket.getPlayerClass());
                    ClientConnection newClient = new ClientConnection(character, address, port);
                    //notify other players that someone connected
                    broadcastPacket(packet, newClient);

                    //notify new connected player about existing players
                    for (ClientConnection client : connectedPlayers) {
                        handler.sendPacket(new ConnectPacket(client.getCharacter().getName(), client.getCharacter().getPlayerClass()), address, port);
                        handler.sendPacket(new MovePacket(client.getCharacter().getName(), client.getCharacter().getLocation().x, client.getCharacter().getLocation().y,
                                (byte) client.getCharacter().getDirection().ordinal()), address, port);
                    }

                    connectedPlayers.add(newClient);

                    break;
                case MOVE:
                    MovePacket movePacket = (MovePacket) packet;
                    ClientConnection movedClient = getClientForPlayerName(movePacket.getUsername());
                    if (movedClient == null) {
                        System.out.println("No client found for: " + movePacket.getUsername());
                    } else {
                        Character player = movedClient.getCharacter();
                        player.setLocation(movePacket.getX(), movePacket.getY());
                        broadcastPacket(packet, movedClient);
                    }
                    break;
                case DISCONNECT:
                    DisconnectPacket disconnectPacket = (DisconnectPacket) packet;
                    ClientConnection disconnectedClient = getClientForPlayerName(disconnectPacket.getUsername());
                    assert disconnectedClient != null;
                    connectedPlayers.remove(disconnectedClient);
                    broadcastPacket(packet, disconnectedClient);
                    break;
                case PROJECTILES:
                    ProjectilesPacket projectilesPacket = (ProjectilesPacket) packet;
                    ClientConnection projectilesClient = getClientForPlayerName(projectilesPacket.getUsername());
                    broadcastPacket(projectilesPacket, projectilesClient);
                    //todo add projectiles to server side players (maybe needed for syncing)
                    break;
                case REGISTER_HIT:
                    HitPacket hitPacket = (HitPacket) packet;
                    ClientConnection sender = getClientForPlayerName(hitPacket.getSender());
                    switch (hitPacket.getHitType()) {
                        case PLAYER_HIT_PLAYER:
                        case MONSTER_HIT_PLAYER:
                            Character target = getClientForPlayerName(hitPacket.getTargetId()).getCharacter();
                            target.damage(hitPacket.getDamage());
                            break;
                        case PLAYER_HIT_MONSTER:
                            Monster monster = getMonsterById(Integer.parseInt(hitPacket.getTargetId()));
                            if (monster != null) {
                                monster.damage(hitPacket.getDamage());
                            }
                            break;


                    }
                    broadcastPacket(hitPacket, sender);
                    //todo clients need to sync player hp every x ms with server
                    break;
                case ITEM_SPAWN:
                    // todo: notify all other players that there's been spawned an item
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Monster getMonsterById(int targetId) {
        if (currentWave != null) {
            for (Monster monster : currentWave.getMonsterList()) {
                if (monster.getId() == targetId) {
                    return monster;
                }
            }
        }
        return null;
    }


    private void syncClientWithConnectedPlayers(ClientConnection reciever) throws IOException {
        for (ClientConnection client : connectedPlayers) {
            if (client != reciever)
                handler.sendPacket(PLAYER_UPDATE_PACKET, client.getAddress(), client.getPort());
        }
    }

    private ClientConnection getClientForPlayerName(String username) {
        for (ClientConnection client : connectedPlayers) {
            if (client.getCharacter().getName().equals(username)) {
                return client;
            }
        }
        return null;
    }

    private void broadcastPacket(Packet packet, ClientConnection origin) {
        connectedPlayers.stream().filter(client -> !client.equals(origin)).forEach(client -> {
            try {
                handler.sendPacket(packet, client.getAddress(), client.getPort());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
