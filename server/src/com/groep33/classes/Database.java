package com.groep33.classes;

import java.sql.*;

/**
 * @author Edwin
 *         Created on 3/30/2016.
 */
public class Database {

    //Fields
    String url = "jdbc:mysql://localhost:3306/yakuza_db";
    String user = "root";
    String pass = "";

    //Constructors
    public Database() throws Exception {
        msg("Database intialized.");
        testSelectQuery();
    }

    //Methods
    public void testSelectQuery() throws Exception {
        try (Connection c = getConnection()) {
            try (Statement s = getStatement(c)) {
                try (ResultSet rs = getResultSet(s, "select * from news_item")) {
                    while (rs.next()) {
                        msg(rs.getString("title") + ", " + rs.getString("content"));
                    }
                }
            }
        }
    }

    public void testUpdateQuery() throws Exception {
        try (Connection c = getConnection()) {
            try (Statement s = getStatement(c)) {
                int rowsAffected = s.executeUpdate(
                        "update employees set email = 'testemail@foo.com' where first_name = 'Edwin' and last_name = 'Van Rooij'");
                msg("Rows affected: " + rowsAffected);
            }
        }
    }

    public void testDeleteQuery() throws Exception {
        try (Connection c = getConnection()) {
            try (Statement s = getStatement(c)) {
                int rowsAffected = s.executeUpdate(
                        "delete from employees where first_name = 'Edwin' and last_name = 'Van Rooij'");
                msg("Rows affected: " + rowsAffected);
            }
        }
    }

    private ResultSet getResultSet(Statement s, String query) throws SQLException {
        return s.executeQuery(query);
    }

    private ResultSet getResultSet(PreparedStatement s) throws SQLException {
        return s.executeQuery();
    }

    private Statement getStatement(Connection c) throws SQLException {
        return c.createStatement();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }

    private void displayResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            msg(rs.getString("name") + ", " + rs.getString("sex"));
        }
    }

    private void msg(String msg) {
        System.out.println(msg);
    }
}