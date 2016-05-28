package com.groeps33.server.application;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.groeps33.server.shared.UserAccount;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Edwin
 *         Created on 3/30/2016.
 */
public class Database {

    private final MysqlDataSource dataSource;

    public Database() {
        dataSource = new MysqlDataSource();
        dataSource.setUser(Constants.USER);
        dataSource.setPassword(Constants.PASS);
        dataSource.setPort(Constants.MYSQL_PORT);
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