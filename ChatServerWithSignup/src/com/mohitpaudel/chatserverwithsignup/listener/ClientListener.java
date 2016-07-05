/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohitpaudel.chatserverwithsignup.listener;

import com.mohitpaudel.chatserverwithsignup.dao.UserDao;
import com.mohitpaudel.chatserverwithsignup.dao.impl.UserDaoImpl;
import com.mohitpaudel.chatserverwithsignup.entity.Client;
import com.mohitpaudel.chatserverwithsignup.entity.User;
import com.mohitpaudel.chatserverwithsignup.handler.ClientHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohit
 */
public class ClientListener extends Thread {

    Socket socket;
    ClientHandler handler;
    PrintStream stream;
    BufferedReader reader;
    UserDao dao = new UserDaoImpl();
    Client client;

    public ClientListener(Socket socket, ClientHandler handler) throws IOException {
        this.socket = socket;
        this.handler = handler;
        stream = new PrintStream(socket.getOutputStream());
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    @Override
    public void run() {

        try {
            stream.println("Welcome to Private Chat Server");
            if (!doLogin()) {
                stream.println("Wrong Username/Password");
            }
            doChat();
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public boolean doLogin() {
        try {
            stream.println("Enter your username");
            String username = reader.readLine();
            stream.println("Enter your password");
            String password = reader.readLine();
            User user = dao.login(username, password);
            if (user != null) {
                client=new Client(socket, username);
                handler.addClients(client);
                return true;
            }

        } catch (SQLException | IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        return false;
    }

    public void doChat() throws IOException{
            while(true)
            {
                stream.print(">");
                String line=reader.readLine();
                handler.broadcastMessage(client,client.getUsername() +"says: " +line);
            }
        
    }

}
