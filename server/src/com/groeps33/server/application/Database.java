package com.groeps33.server.application;

import com.groeps33.server.shared.UserAccount;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * @author Edwin
 *         Created on 3/30/2016.
 */
public class Database {

    private final MysqlDataSource dataSource;
    private static final String ACCOUNT_ID = "id";
    private static final String ACCOUNT_USERNAME = "username";
    public static final String ACCOUNT_PASSWORD = "password";
    public static final String ACCOUNT_EMAIL = "email";
    public static final String ACCOUNT_DOUBLOONS = "doubloons";
    public static final String ACCOUNT_NAME_CHANGE = "name_change";
    public static final String ACCOUNT_NAME_COLOR = "name_color";

    public Database() {
        dataSource = new MysqlDataSource();
        dataSource.setUser(Constants.USER);
        dataSource.setPassword(Constants.PASS);
        dataSource.setPort(Constants.MYSQL_PORT);
    }

    public UserAccount register(String username, String email, String password) {
        try (Connection c = dataSource.getConnection()) {
            try (Statement s = c.createStatement()) {
                int rowsAffected = s.executeUpdate(String.format("INSERT INTO `the-valley-db`.`account` (`%s`, `%s`, `%s`) VALUES ('%s', '%s', '%s')", ACCOUNT_USERNAME, ACCOUNT_PASSWORD, ACCOUNT_EMAIL,  username, email, password));
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

    public UserAccount login(String username, String password) throws Exception {
        try (Connection c = dataSource.getConnection()) {
            try (Statement s = c.createStatement()) {
                String query = String.format("SELECT * FROM `the-valley-db`.account WHERE %s = '%s' AND %s = '%s'", ACCOUNT_USERNAME, username, ACCOUNT_PASSWORD, password);
                try (ResultSet rs = s.executeQuery(query)) {
                    if (!rs.next()) {
                        System.out.format("No account was found for username %s and password %s", username, password);
                        return null;
                    } else {
                        String email = rs.getString(ACCOUNT_EMAIL);
                        System.out.format("Successfully returned account with username %s and email %s", username, email);
                        return new UserAccount(username, email);
                    }
                }
            }
        }
    }
}