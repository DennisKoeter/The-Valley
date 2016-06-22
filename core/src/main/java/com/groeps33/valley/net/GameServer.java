package com.groeps33.valley.net;

import com.badlogic.gdx.math.Vector2;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.Monster;
import com.groeps33.valley.net.packet.*;
import com.groeps33.valley.util.Calculations;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameServer implements PacketListener {

    public final static int SERVER_PORT = 8009;
    private static final Packet PLAYER_UPDATE_PACKET = new RequestPlayerUpdate();

    private final static Vector2 MONSTER_SPAWN = new Vector2(309, 1355);


    private final DatagramSocket serverSocket;
    private final List<ClientConnection> connectedPlayers = new CopyOnWriteArrayList<>();
    private final List<Monster> monsters = new CopyOnWriteArrayList<>();
    private final PacketHandler handler;

    private Wave currentWave;

    public GameServer() throws IOException {
        this.serverSocket = new DatagramSocket(SERVER_PORT);
        handler = new PacketHandler(serverSocket);
        handler.addListener(this);
        handler.start();

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
        }, 0, 500);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                tick();
//            }
//        }, 0, 100);
    }



    private void tick() {
        if (currentWave == null) {
            currentWave = new Wave(1, System.currentTimeMillis() + 500);
            broadcastPacket(new NewWave(1), null);
        }
        for (Monster monster : currentWave.getMonsterList()) {
            ClientConnection closest = null;
            double dist = Double.MAX_VALUE;
            for (ClientConnection clientConnection : connectedPlayers) {
                double t = Calculations.distance(clientConnection.getCharacter().getLocation(), monster.getLocation());
                if (t < dist) {
                    dist = t;
                    closest = clientConnection;
                }
            }

            if (closest != null && !closest.getCharacter().getName().equals(monster.getTarget())) {
                monster.setTarget(closest.getCharacter().getName());
                broadcastPacket(new MonsterTargetUpdate(monster.getId(), monster.getTarget()), null);
            }
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
                    assert movedClient != null;
                    Character player = movedClient.getCharacter();
                    player.setLocation(movePacket.getX(), movePacket.getY());
                    broadcastPacket(packet, movedClient);
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
                case PLAYER_HIT:
                    HitPacket hitPacket = (HitPacket) packet;
                    ClientConnection sender = getClientForPlayerName(hitPacket.getSender());
                    switch (hitPacket.getHitType()) {
                        case PLAYER_HIT_PLAYER:
                            Character target = getClientForPlayerName(hitPacket.getTargetId()).getCharacter();
                            target.damage(hitPacket.getDamage());
                            break;
                        case PLAYER_HIT_MONSTER:
                            Monster monster = getMonsterById(hitPacket.getTargetId());
                            assert monster != null;

                            //todo

                    }
                    broadcastPacket(hitPacket, sender);
                    //todo clients need to sync player hp every x ms with server
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Monster getMonsterById(String targetId) {
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
