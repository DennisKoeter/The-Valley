package com.groeps33.valley.net.packet;

import com.groeps33.valley.entity.PlayerClass;

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
    private PlayerClass playerClass;

    public ConnectPacket(byte[] data) throws IOException {
        super(PacketType.CONNECT, data);
    }

    public ConnectPacket(String username, PlayerClass playerClass) {
        super(PacketType.CONNECT);
        this.username = username;
        this.playerClass = playerClass;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        this.username = dis.readUTF();
        playerClass = PlayerClass.values()[dis.readByte()];
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeUTF(username);
        dos.writeByte(playerClass.ordinal());
    }

    public String getUsername() {
        return username;
    }

    public PlayerClass getPlayerClass() {
        return playerClass;
    }
}
