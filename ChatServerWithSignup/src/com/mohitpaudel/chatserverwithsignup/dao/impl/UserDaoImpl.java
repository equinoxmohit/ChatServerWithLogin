/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohitpaudel.chatserverwithsignup.dao.impl;

import com.mohitpaudel.chatserverwithsignup.connector.JConnector;
import com.mohitpaudel.chatserverwithsignup.dao.UserDao;
import com.mohitpaudel.chatserverwithsignup.entity.User;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mohit
 */
public class UserDaoImpl implements UserDao {

    JConnector conn = new JConnector();

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ArrayList<User> user = new ArrayList<>();
        conn.open();
        String sql = "SELECT * FROM tbl_users";
        PreparedStatement st = conn.initStatements(sql);
        ResultSet rs = conn.query();
        while (rs.next()) {
            User users = new User();
            users.setUsername(rs.getString("username"));
            users.setPassword(rs.getString("password"));
            users.setConfirmPass(rs.getString("confirm_password"));
            user.add(users);
        }
        conn.close();
        return user;

    }

    @Override
    public User login(String username, String password) throws SQLException {
        for (User us : getAll()) {
            if ((us.getUsername().equals(username)) && (us.getPassword().equals(password))) {
                return us;
            }
        }
        return null;
    }

}
