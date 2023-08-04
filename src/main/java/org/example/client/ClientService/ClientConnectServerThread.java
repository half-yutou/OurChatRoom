package org.example.client.ClientService;

import org.example.common.Message;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread{
    //需要维护一个socket
    private Socket socket;

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        //线程需要在后台和服务器通信，用死循环控制
        while(true) {
            System.out.println("客户端线程等待读取服务端信息");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();//如果服务器没有发送对象，线程会阻塞在这里
                //现在这个message还没开始使用，比如说后续可以将其输出在客户端控制台
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
