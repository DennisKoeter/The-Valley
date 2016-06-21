package com.groeps33.valley.net;

import com.groeps33.valley.TheValleyGame;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.net.packet.*;
import com.groeps33.valley.screens.GameScreen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

    public GameClient(GameScreen game, String host) throws IOException {
        this.game = game;
        socket = new DatagramSocket();
        address = InetAddress.getByName(host);
        connectedPlayers = new ArrayList<>();
        handler = new PacketHandler(socket);
        handler.addListener(this);
        handler.start();
    }

    @Override
    public void onPacketReceived(Packet packet, InetAddress address, int port) {
        switch (packet.getType()) {
            case CONNECT:
                ConnectPacket connectPacket = (ConnectPacket) packet;
                game.addPlayer(connectPacket.getUsername());
                break;
            case MOVE:
                MovePacket movePacket = (MovePacket) packet;
                game.playerMoved(movePacket.getUsername(), movePacket.getX(), movePacket.getY(), movePacket.getDirection());
                break;
            case DISCONNECT:
                DisconnectPacket disconnectPacket = (DisconnectPacket) packet;
                game.removePlayer(disconnectPacket.getUsername());
        }
    }

    public void update(Character character) {
        //update just consists of move yet
        try {
            sendPacket(new MovePacket(character.getName(), character.getLocation().x, character.getLocation().y, (byte) character.getDirection().ordinal()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(Character character) {
        try {
            sendPacket(new ConnectPacket(character.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendPacket(Packet packet) throws IOException {
        handler.sendPacket(packet, address, GameServer.SERVER_PORT);
    }
}