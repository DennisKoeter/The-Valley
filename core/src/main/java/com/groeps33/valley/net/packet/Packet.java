package com.groeps33.valley.net.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/15/2016.
 *
 * A packet containing information to be sent over a network.
 *
 * @author Bram Hoendervangers
 */
public abstract class Packet {
    protected final PacketType type;
    private final int size;

    Packet(PacketType type, byte[] data) throws IOException {
        this.type = type;
        this.size = data.length;
        try (DataInputStream dis =new DataInputStream(new ByteArrayInputStream(data))) {
            if (type.ordinal() != (int) dis.readByte()) {
                throw new IllegalArgumentException("Packet should have the following type: " + type);
            }
            readData(dis);
        }
    }

    public Packet(PacketType type) {
        this.type = type;
        this.size = -1;
    }


    protected abstract void readData(DataInputStream dis) throws IOException;
    public abstract void writeData(DataOutputStream dos) throws IOException;

    public PacketType getType() {
        return type;
    }

    public int getSize() {
        return size;
    }
}
