package com.groeps33.valley.net;

import com.groeps33.valley.entity.Character;
import com.groeps33.valley.entity.Projectile;
import com.groeps33.valley.net.packet.*;
import com.groeps33.valley.screens.GameScreen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameClient implements PacketListener {

    private final DatagramSocket socket;
    private final GameScreen game;
    private final InetAddress address;
    private final List<Character> connectedPlayers;
    private final PacketHandler handler;
    private AtomicBoolean serverConnected = new AtomicBoolean();

    public GameClient(GameScreen game, String host) throws IOException {
        this.game = game;
        socket = new DatagramSocket();
        address = InetAddress.getByName(host);
        connectedPlayers = new ArrayList<>();
        handler = new PacketHandler(socket);
        handler.addListener(this);
        handler.start();
        new Thread(this::waitOnServer).start();
    }

    private void waitOnServer() {
        while (true) {
            try {
                if (serverConnected.get()) {
                    return;
                }
                handler.sendData(new byte[]{ 7}, address, GameServer.SERVER_PORT);
                Thread.sleep(200);
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onPingReceived(InetAddress address, int port) {
        System.out.println("ping recieved");
        if (this.address.equals(address)) {
            serverConnected.set(true);
            connect(game.getLocalPlayer());
        }
    }

    @Override
    public void onPacketReceived(Packet packet, InetAddress address, int port) {
        switch (packet.getType()) {
            case CONNECT:
                ConnectPacket connectPacket = (ConnectPacket) packet;
                game.addPlayer(connectPacket.getUsername(), connectPacket.getPlayerClass());
                break;
            case MOVE:
                MovePacket movePacket = (MovePacket) packet;
                game.playerMoved(movePacket.getUsername(), movePacket.getX(), movePacket.getY(), movePacket.getDirection());
                break;
            case DISCONNECT:
                DisconnectPacket disconnectPacket = (DisconnectPacket) packet;
                game.removePlayer(disconnectPacket.getUsername());
                break;
            case PROJECTILES:
                ProjectilesPacket projectilesPacket = (ProjectilesPacket) packet;
                game.setProjectiles(projectilesPacket.getUsername(), projectilesPacket.getProjectileX(), projectilesPacket.getProjectileY());
                break;
            case PLAYER_HIT:
                HitPacket hitPacket = (HitPacket) packet;
                game.registerHit(hitPacket.getSender(), hitPacket.getDamage());
                break;
            case REQUEST_UPDATE:
                update(game.getLocalPlayer());
                break;
            case NEW_WAVE:
                game.registerNewWave(((NewWave) packet).getNumber());
        }
    }

    public void update(Character character) {
        //update just consists of move yet
        try {
            sendPacket(new MovePacket(character.getName(), character.getLocation().x, character.getLocation().y, (byte) character.getDirection().ordinal()));
//            if (!character.getProjectiles().isEmpty()) {
            float[] projectileX = new float[character.getProjectiles().size()];
            float[] projectileY = new float[character.getProjectiles().size()];
            for (int i = 0; i < projectileX.length; i++) {
                projectileX[i] = character.getProjectiles().get(i).getLocation().x;
                projectileY[i] = character.getProjectiles().get(i).getLocation().y;
            }
            sendPacket(new ProjectilesPacket(character.getName(), projectileX, projectileY));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(Character character) {
        try {
            sendPacket(new ConnectPacket(character.getName(), character.getPlayerClass()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updatePlayerHit(Character localPlayer, Character target, Projectile projectile) {
        try {
            sendPacket(new HitPacket(localPlayer.getName(), target.getName(), projectile.getDamage(), HitPacket.HitType.PLAYER_HIT_PLAYER));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPacket(Packet packet) throws IOException {
        handler.sendPacket(packet, address, GameServer.SERVER_PORT);
    }

    public void disconnect(Character character) {
        disconnect(character.getName());
    }

    public void disconnect(String username) {
        try {
            sendPacket(new DisconnectPacket(username));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
