/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mohitpaudel.chatserverwithsignup;

import com.mohitpaudel.chatserverwithsignup.connector.JConnector;
import com.mohitpaudel.chatserverwithsignup.dao.UserDao;
import com.mohitpaudel.chatserverwithsignup.dao.impl.UserDaoImpl;
import com.mohitpaudel.chatserverwithsignup.handler.ClientHandler;
import com.mohitpaudel.chatserverwithsignup.listener.ClientListener;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Mohit
 */
public class Program extends JFrame {

    JLabel username, password, confirmPassword;
    JTextField txtUsername;
    JPasswordField txtPassword, txtConfirm;
    JButton signUp, cmd;
    JConnector conn = new JConnector();

    public Program() {
        initComponents();
    }

    public void initComponents() {
        username = new JLabel("Username");
        username.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        password = new JLabel("Password");
        password.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        txtUsername = new JTextField();
        txtUsername.setColumns(15);
        txtPassword = new JPasswordField();
        txtPassword.setColumns(15);
        signUp = new JButton("Sign Up");
        cmd = new JButton("Open cmd");
        confirmPassword = new JLabel("Confirm Password");
        confirmPassword.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        txtConfirm = new JPasswordField();
        txtConfirm.setColumns(15);

        Container container = getContentPane();
        container.add(username);
        container.add(txtUsername);
        container.add(password);
        container.add(txtPassword);
        container.add(confirmPassword);
        container.add(txtConfirm);
        container.add(signUp);
        container.add(cmd);

        setTitle("ChatServer With Sign Up");
        setSize(1100, 200);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

        signUp.addActionListener(new Signup());
        cmd.addActionListener(new Cmd());

    }

    public static void main(String[] args) {
        Program program = new Program();

        int port = 12321;
        try {
            ServerSocket server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();

                ClientHandler handler = new ClientHandler();
                ClientListener listener = new ClientListener(socket, handler);
                listener.start();

            }
        } catch (IOException ex) {
            System.out.println(ex.getLocalizedMessage());
        }

    }

    public class Signup implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            conn.open();
            String sql = "INSERT INTO tbl_users(username,password,confirm_password)" + "VALUES(?,?,?)";
            try {
                PreparedStatement st;

                st = conn.initStatements(sql);
                st.setString(1, txtUsername.getText());
                st.setString(2, txtPassword.getText());
                st.setString(3, txtConfirm.getText());
                if (txtPassword.getText().equals(txtConfirm.getText())) {
                    int result = conn.update();
                    System.out.println(result);
                    JOptionPane.showMessageDialog(null, "Congratulations..Signup succesful");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtConfirm.setText("");

                } else {
                    JOptionPane.showMessageDialog(null, "The password doesnt matches.Please try again");
                    txtUsername.setText("");
                    txtPassword.setText("");
                    txtConfirm.setText("");
                }

                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public class Cmd implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UserDao dao = new UserDaoImpl();
            try {
                dao.getAll();
                Runtime rt = Runtime.getRuntime();
                rt.exec(new String[]{"cmd.exe", "/c", "start"});
            } catch (SQLException | IOException ex) {
                System.out.println(ex.getLocalizedMessage());

            }

        }

    }
}
