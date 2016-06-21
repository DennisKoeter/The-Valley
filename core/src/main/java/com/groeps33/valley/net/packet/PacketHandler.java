package com.groeps33.valley.net.packet;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class PacketHandler extends Thread {
    private final DatagramSocket socket;
    private List<PacketListener> listeners = new ArrayList<>();
    private boolean stopped = false;

    public PacketHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    public void addListener(PacketListener listener) {
        listeners.add(listener);
    }

    public void removeListener(PacketListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        while (!stopped) {
            byte[] data = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
            try {
                socket.receive(datagramPacket);
                Packet packet = parsePacket(datagramPacket);
                listeners.forEach(l -> l.onPacketReceived(packet, datagramPacket.getAddress(), datagramPacket.getPort()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Packet parsePacket(DatagramPacket datagramPacket) throws IOException {
        PacketType type = PacketType.values()[datagramPacket.getData()[0]];
        switch (type) {
            case CONNECT:
                return new ConnectPacket(datagramPacket.getData());
            case MOVE:
                return new MovePacket(datagramPacket.getData());

        }
        return null;
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) throws IOException {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        socket.send(packet);
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public void sendPacket(Packet packet, InetAddress address, int port) throws IOException {
        try (ByteArrayOutputStream bos = packet.getSize() == -1 ? new ByteArrayOutputStream() : new ByteArrayOutputStream(packet.getSize());
             DataOutputStream dos = new DataOutputStream(bos)) {
            dos.writeByte(packet.getType().ordinal());
            packet.writeData(dos);
            sendData(bos.toByteArray(), address, port);
        }
    }
}
