package com.groep33.classes;

import com.groep33.interfaces.IUserAccount;
import com.mysql.jdbc.*;
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
    public Database() throws Exception {
        dataSource = new MysqlDataSource();
        dataSource.setUser(USER);
        dataSource.setPassword(PASS);
        dataSource.setPort(3306);
    }

    public IUserAccount register(String username, String email, String password) {
        try (Connection c = dataSource.getConnection()) {
            try (Statement s = c.createStatement()) {
                ResultSet result = s.executeQuery(String.format("INSERT INTO `the-valley-db`.`account` (`username`, `password`, `email`) VALUES ('%s', '%s', '%s')", username, email, password));
                if (result.next()) {
                    // todo: fix returnen van useraccountg
                }
//                INSERT INTO `the-valley-db`.`account` (`username`, `password`, `email`) VALUES ('user', 'pass', 'email');


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}