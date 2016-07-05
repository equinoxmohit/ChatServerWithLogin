/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohitpaudel.chatserverwithsignup.dao;

import com.mohitpaudel.chatserverwithsignup.entity.User;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mohit
 */
public interface UserDao {

    ArrayList<User> getAll() throws SQLException;

    User login(String username, String password) throws SQLException;

}
