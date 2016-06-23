package com.groeps33.valley.net;

import com.groeps33.valley.Constants;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class ClientTest {
    private static InetAddress address;
    private static DatagramSocket socket;

    public static void main(String[] args) throws SocketException, UnknownHostException {

        address = InetAddress.getByName(Constants.HOST_IP);
        socket = new DatagramSocket();

//        while (true) {
//            byte[] data = new byte[1024];
//            DatagramPacket packet = new DatagramPacket(data, data.length);
//            try {
//                socket.receive(packet);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            // System.out.println("SERVER > "+new String(packet.getData()));
//        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream(4);
        DataOutputStream dos = new DataOutputStream(bos);
        try {
            dos.writeInt(2323);
            for (int i = 0; i < 10; i++) {
                sendData(bos.toByteArray());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length,
                address, 8009);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
