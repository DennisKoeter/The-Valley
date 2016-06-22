package com.groeps33.valley.net;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class ServerTest {

    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket(8009);
        while (true) {
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                System.out.println(packet.getLength());
                System.out.println(Arrays.toString(packet.getData()));
                DataInputStream bis = new DataInputStream(new ByteArrayInputStream(packet.getData()));
                System.out.println("result: " + bis.readInt());
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
