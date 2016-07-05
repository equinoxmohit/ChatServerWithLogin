/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohitpaudel.chatserverwithsignup.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Mohit
 */
public class JConnector {

    Connection conn = null;
    PreparedStatement stmnt;

    public void open() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/usersdb", "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public PreparedStatement initStatements(String sql) throws SQLException {
        stmnt = conn.prepareStatement(sql);
        return stmnt;
    }

    public int update() throws SQLException {
        int result = stmnt.executeUpdate();
        return result;
    }

    public ResultSet query() throws SQLException {
        return stmnt.executeQuery();
    }

    public void close() throws SQLException {
        if (conn != null && conn.isClosed()) {
            conn.close();
            conn = null;
        }
    }

}
