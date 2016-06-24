package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/24/2016.
 *
 * @author Bram Hoendervangers
 */
public class MonstersUpdatePacket extends Packet {
    private int[] ids;
    private float[] locsY;
    private float[] locsX;
    private Object XLocs;

    public MonstersUpdatePacket(byte[] data) throws IOException {
        super(PacketType.MONSTERS_UPDATE, data);
    }

    public MonstersUpdatePacket(int[] ids, float[] locsX, float[] locsY) {
        super(PacketType.MONSTERS_UPDATE);
        this.ids = ids;
        this.locsX = locsX;
        this.locsY = locsY;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        byte size = dis.readByte();
        ids = new int[size];
        locsX = new float[size];
        locsY = new float[size];
        for (int i = 0; i < size; i++) {
            ids[i] = dis.readInt();
            locsX[i] = dis.readFloat();
            locsY[i] = dis.readFloat();
        }

    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeByte(locsX.length);
        for (int i = 0; i < locsX.length; i++) {
            dos.writeInt(ids[i]);
            dos.writeFloat(locsX[i]);
            dos.writeFloat(locsY[i]);
        }
    }

    public int[] getIds() {
        return ids;
    }

    public float[] getXLocs() {
        return locsX;
    }


    public float[] getYLocs() {
        return locsY;
    }
}
