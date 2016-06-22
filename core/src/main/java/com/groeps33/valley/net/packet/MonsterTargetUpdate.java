package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class MonsterTargetUpdate extends Packet{
    private int id;
    private String target;

    public MonsterTargetUpdate(byte[] data) throws IOException {
        super(PacketType.MONSTER_TARGET_UPDATE, data);
    }

    public MonsterTargetUpdate(int id, String target) {
        super(PacketType.MONSTER_TARGET_UPDATE);
        this.id = id;
        this.target = target;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        id = dis.readShort();
        target = dis.readUTF();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeShort(id);
        dos.writeUTF(target);
    }

    public int getId() {
        return id;
    }

    public String getTarget() {
        return target;
    }
}
