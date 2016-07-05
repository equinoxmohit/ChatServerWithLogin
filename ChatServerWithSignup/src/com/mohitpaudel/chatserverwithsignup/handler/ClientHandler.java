/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohitpaudel.chatserverwithsignup.handler;

import com.mohitpaudel.chatserverwithsignup.entity.Client;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Mohit
 */
public class ClientHandler {

    ArrayList<Client> client = new ArrayList<>();

    public boolean addClients(Client c) {
        client.add(c);
        return true;
    }

    public Client getBySocket(Socket socket) {
        for (Client cl : client) {
            if ((cl.getSocket().equals(socket))) {
                return cl;
            }

        }
        return null;
    }

    public Client getByUserName(String username) {
        for (Client cl : client) {
            if ((cl.getUsername().equals(username))) {
                return cl;
            }

        }
        return null;
    }

    public void broadcastMessage(Client client_,String message) throws IOException
    {
            for(Client cli:client)
            {
                if(cli.equals(client_))
                {
                    PrintStream  output=new PrintStream(cli.getSocket().getOutputStream());
                    output.println(message);
                }
            
            }

    }
        
    }

    
    
