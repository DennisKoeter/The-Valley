package com.groeps33.valley.net.packet;

import java.net.InetAddress;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public interface PacketListener {

    void onPacketReceived(Packet packet, InetAddress address, int port);
}
