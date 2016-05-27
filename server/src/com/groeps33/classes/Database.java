package com.groeps33.classes;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;

/**
 * @author Edwin
 *         Created on 3/30/2016.
 */
public class Database {

    //Fields

    private final MysqlDataSource dataSource;

    private static final String USER = "the-valley-user";
    private static final String PASS = "";

    //Constructors
    public Database() {
        dataSource = new MysqlDataSource();
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        dataSource.setPort(3306);
    }

    public UserAccount register(String username, String email, String password) {
        try (Connection c = dataSource.getConnection()) {
            try (Statement s = c.createStatement()) {
                int rowsAffected = s.executeUpdate(String.format("INSERT INTO `the-valley-db`.`account` (`username`, `password`, `email`) VALUES ('%s', '%s', '%s')", username, email, password));
                if (rowsAffected == 1) {
                    return new UserAccount(username, email);
                } else {
                    System.out.println("weird shit happened");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}