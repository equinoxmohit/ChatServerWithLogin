/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohitpaudel.chatserverwithsignup.entity;

/**
 *
 * @author Mohit
 */
public class User {

    private String username, password, confirmPass;

    public User() {
    }

    public User(String username, String password, String confirmPass) {
        this.username = username;
        this.password = password;
        this.confirmPass = confirmPass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

}
