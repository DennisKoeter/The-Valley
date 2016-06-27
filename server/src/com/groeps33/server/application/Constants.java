package com.groeps33.server.application;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Edwin
 */
public class Constants {
    private static Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }

    public final int PORT_NUMBER = 1099;
    public final String LOBBY_ADMIN_NAME = "LobbyAdmin";
    static final String GAME_ADMIN_NAME = "GameAdmin";

    String user;
    String pass;
    String database;
    final int MYSQL_PORT = 3306;

    private Properties props = new Properties();

    private Constants() {
        try (InputStream input = new FileInputStream("dbconfig.properties")){
            props.load(input);
            user = props.getProperty("username");
            pass = props.getProperty("password");
            database = props.getProperty("database");
//            System.out.format("User was '%s', pass '%s', dbname '%s'\r\n", user, pass, database);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
