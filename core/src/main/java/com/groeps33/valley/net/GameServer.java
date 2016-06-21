package com.groeps33.valley.net;

import com.groeps33.valley.TheValleyGame;
import com.groeps33.valley.entity.Character;
import com.groeps33.valley.net.packet.*;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameServer implements PacketListener {

    public final static int SERVER_PORT = 8009;

    private class ClientConnection {
        private final Character character;
        private final InetAddress address;
        private final int port;

        ClientConnection(Character character, InetAddress address, int port) {
            this.character = character;
            this.address = address;
            this.port = port;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ClientConnection)) return false;

            ClientConnection that = (ClientConnection) o;

            return port == that.port && (address != null ? address.equals(that.address) : that.address == null);

        }

        @Override
        public int hashCode() {
            int result = address != null ? address.hashCode() : 0;
            result = 31 * result + port;
            return result;
        }
    }

    private final DatagramSocket serverSocket;
    private final List<ClientConnection> connectedPlayers = new ArrayList<>();
    private final PacketHandler handler;

    public GameServer() throws IOException {
        this.serverSocket = new DatagramSocket(SERVER_PORT);
        handler = new PacketHandler(serverSocket);
        handler.addListener(this);
        handler.start();
    }

    @Override
    public void onPacketReceived(Packet packet, InetAddress address, int port) {

        try {
            switch (packet.getType()) {
                case CONNECT:
                    ConnectPacket connectPacket = (ConnectPacket) packet;
                    System.out.println("server: connect -> " + connectPacket.getUsername());
                    Character character = new Character(-1, -1, connectPacket.getUsername());
                    ClientConnection newClient = new ClientConnection(character, address, port);
                    //notify other players that someone connected
                    broadcastPacket(packet, newClient);

                    //notify new connected player about existing players
                    for (ClientConnection client : connectedPlayers) {
                        handler.sendPacket(new ConnectPacket(client.character.getName()), address, port);
                        handler.sendPacket(new MovePacket(client.character.getName(), client.character.getLocation().x, client.character.getLocation().y,
                                (byte) client.character.getDirection().ordinal()), address, port);
                    }

                    connectedPlayers.add(newClient);

                    break;
                case MOVE:
                    MovePacket movePacket = (MovePacket) packet;
                    ClientConnection movedClient = getClientForPlayerName(movePacket.getUsername());
                    assert movedClient != null;
                    Character player = movedClient.character;
                    player.setLocation(movePacket.getX(), movePacket.getY());
                    broadcastPacket(packet, movedClient);
                    break;
                case DISCONNECT:
                    DisconnectPacket disconnectPacket = (DisconnectPacket) packet;
                    ClientConnection disconnectedClient = getClientForPlayerName(disconnectPacket.getUsername());
                    assert disconnectedClient != null;
                    connectedPlayers.remove(disconnectedClient);
                    broadcastPacket(packet, disconnectedClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ClientConnection getClientForPlayerName(String username) {
        for (ClientConnection client : connectedPlayers) {
            if (client.character.getName().equals(username)) {
                return client;
            }
        }
        return null;
    }

    private void broadcastPacket(Packet packet, ClientConnection origin) {
        connectedPlayers.stream().filter(client -> !client.equals(origin)).forEach(client -> {
            try {
                handler.sendPacket(packet, client.address, client.port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
