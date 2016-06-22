package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class ItemSpawn extends Packet {

    public ItemSpawn(byte[] data) throws IOException {
        super(PacketType.ITEM_SPAWN, data);
    }

    public ItemSpawn() {
        super(PacketType.PLAYER_HIT);
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {

    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
    }
}