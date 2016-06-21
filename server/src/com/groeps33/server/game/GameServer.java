package com.groeps33.server.game;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by Bram on 6/15/2016.
 *
 * @author Bram Hoendervangers
 */
public class GameServer {

    private final ServerSocket serverSocket;

    public GameServer() throws IOException {
        this.serverSocket = new ServerSocket(8009);
    }


}
