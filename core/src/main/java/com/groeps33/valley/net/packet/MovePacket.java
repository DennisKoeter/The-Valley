package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class MovePacket extends Packet {

    private String username;
    private float x;
    private float y;
    private byte direction;

    public MovePacket(byte[] data) throws IOException {
        super(PacketType.MOVE, data);
    }

    public MovePacket(String username, float x, float y, byte direction) {
        super(PacketType.MOVE);
        this.username = username;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        username = dis.readUTF();
        x = dis.readFloat();
        y = dis.readFloat();
        direction = dis.readByte();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeUTF(username);
        dos.writeFloat(x);
        dos.writeFloat(y);
        dos.writeByte(direction);
    }

    public String getUsername() {
        return username;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public byte getDirection() {
        return direction;
    }
}
