package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class NewWave extends Packet {
    private int number;

    NewWave(byte[] data) throws IOException {
        super(PacketType.NEW_WAVE, data);
    }

    public NewWave(int number) {
        super(PacketType.NEW_WAVE);
        this.number = number;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        number = dis.readShort();
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeShort(number);
    }

    public int getNumber() {
        return number;
    }
}
