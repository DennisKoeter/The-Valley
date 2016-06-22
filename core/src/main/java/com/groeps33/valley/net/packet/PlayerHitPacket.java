package com.groeps33.valley.net.packet;

import com.groeps33.valley.net.packet.Packet;
import com.groeps33.valley.net.packet.PacketType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class PlayerHitPacket extends Packet {
    private String username;
    private String targetName;
    private int damage;

    public PlayerHitPacket(byte[] data) throws IOException {
        super(PacketType.PLAYER_HIT, data);
    }

    public PlayerHitPacket(String username, String targetName, int damage) {
        super(PacketType.PLAYER_HIT);
        this.username = username;
        this.targetName = targetName;
        this.damage = damage;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        username = dis.readUTF();
        targetName = dis.readUTF();
        damage = dis.readInt();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeUTF(username);
        dos.writeUTF(targetName);
        dos.writeInt(damage);
    }

    public String getUsername() {
        return username;
    }

    public String getTargetName() {
        return targetName;
    }

    public int getDamage() {
        return damage;
    }
}
