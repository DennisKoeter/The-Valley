package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class RequestPlayerUpdate extends Packet {

    public RequestPlayerUpdate(byte[] data) throws IOException {
        super(PacketType.REQUEST_UPDATE, data);
    }

    public RequestPlayerUpdate() {
        super(PacketType.REQUEST_UPDATE);
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {

    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {

    }
}
