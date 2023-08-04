package org.example.server.ServerService;

/*
该类生成的对象和某个客户端保持通信
 */


import org.example.common.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnectClientThread extends Thread {

    private Socket socket;

    private String userID;//链接到服务端的ID

    public ServerConnectClientThread(Socket socket, String userID) {
        this.socket = socket;
        this.userID = userID;
    }

    @Override
    public void run() {//线程处于run状态，可以发送/接收客户端
        while(true) {
            System.out.println("服务端与用户" + userID + "保持通讯中");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
