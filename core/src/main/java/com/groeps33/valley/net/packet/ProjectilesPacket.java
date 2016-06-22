package com.groeps33.valley.net.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
public class ProjectilesPacket extends Packet {
    private String username;
    private float[] projectileX;
    private float[] projectileY;

    public ProjectilesPacket(byte[] data) throws IOException {
        super(PacketType.PROJECTILES, data);
    }

    public ProjectilesPacket(String username, float[] projectileX, float[] projectileY) {
        super(PacketType.PROJECTILES);
        this.username = username;
        this.projectileX = projectileX;
        this.projectileY = projectileY;
    }

    @Override
    protected void readData(DataInputStream dis) throws IOException {
        username = dis.readUTF();
        byte size = dis.readByte();
        projectileX = new float[size];
        projectileY = new float[size];
        for (int i = 0; i < size; i++) {
            projectileX[i] = dis.readFloat();
        }

        for (int i = 0; i < size; i++) {
            projectileY[i] = dis.readFloat();
        }
    }

    @Override
    public void writeData(DataOutputStream dos) throws IOException {
        dos.writeUTF(username);
        dos.writeByte(projectileX.length);

        for (float x : projectileX) {
            dos.writeFloat(x);
        }


        for (float y : projectileY) {
            dos.writeFloat(y);
        }
    }

    public String getUsername() {
        return username;
    }

    public float[] getProjectileX() {
        return projectileX;
    }

    public float[] getProjectileY() {
        return projectileY;
    }
}
