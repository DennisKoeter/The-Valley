package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class ConnectPacket extends Packet{

    private String username;
    public ConnectPacket(byte[] data) throws IOException {
        super(PacketType.CONNECT, data);
    }

    public ConnectPacket(String username) {
        super(PacketType.CONNECT);
        this.username = username;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        this.username = dis.readUTF();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeUTF(username);
    }

    public String getUsername() {
        return username;
    }
}
