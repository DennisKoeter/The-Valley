package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class DisconnectPacket extends Packet {

    private String username;

    public DisconnectPacket(byte[] data) throws IOException {
        super(PacketType.DISCONNECT, data);
    }

    public DisconnectPacket(String username) {
        super(PacketType.DISCONNECT);
        this.username = username;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        username = dis.readUTF();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeUTF(username);
    }

    public String getUsername() {
        return username;
    }
}
