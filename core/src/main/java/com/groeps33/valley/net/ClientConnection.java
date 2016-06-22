package com.groeps33.valley.net;

import com.groeps33.valley.entity.Character;

import java.net.InetAddress;

/**
 * Created by Bram on 6/22/2016.
 *
 * @author Bram Hoendervangers
 */
class ClientConnection {
    private final Character character;
    private final InetAddress address;
    private final int port;

    ClientConnection(Character character, InetAddress address, int port) {
        this.character = character;
        this.address = address;
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientConnection)) return false;

        ClientConnection that = (ClientConnection) o;

        return port == that.port && (address != null ? address.equals(that.address) : that.address == null);

    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }

    public Character getCharacter() {
        return character;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getPort() {
        return port;
    }
}
