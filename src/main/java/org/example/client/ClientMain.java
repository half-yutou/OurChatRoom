package org.example.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

       LoginInterface loginInterface = new LoginInterface();
       loginInterface.mainMenu();

    }
}
