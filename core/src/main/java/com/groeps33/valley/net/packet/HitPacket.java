package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class HitPacket extends Packet {
    private String sender;
    private String targetId;
    private int damage;
    private HitType hitType;

    public enum HitType {
        PLAYER_HIT_PLAYER,
        PLAYER_HIT_MONSTER,
        MONSTER_HIT_PLAYER
    }

    public HitPacket(byte[] data) throws IOException {
        super(PacketType.REGISTER_HIT, data);
    }

    public HitPacket(String sender, String targetName, int damage, HitType hitType) {
        super(PacketType.REGISTER_HIT);
        this.sender = sender;
        this.targetId = targetName;
        this.damage = damage;
        this.hitType = hitType;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        sender = dis.readUTF();
        targetId = dis.readUTF();
        damage = dis.readInt();
        hitType = HitType.values()[dis.readByte()];
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeUTF(sender);
        dos.writeUTF(targetId);
        dos.writeInt(damage);
        dos.writeByte(hitType.ordinal());
    }

    public String getSender() {
        return sender;
    }

    public String getTargetId() {
        return targetId;
    }

    public int getDamage() {
        return damage;
    }

    public HitType getHitType() {
        return hitType;
    }
}
