package org.example.client.ClientService;

import org.example.common.Message;
import org.example.common.MessageType;

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
            //System.out.println("客户端线程等待读取服务端信息");
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();//如果服务器没有发送对象，线程会阻塞在这里

                //根据message不同的类型，做不同的业务处理

                //1.message是 服务端返回的在线用户列表
                if(message.getType().equals(MessageType.MESSAGE_RETURN_ONLINE_USER)){
                    //读取在线用户信息
                    String[] onlineUsers = message.getContent().split(" ");
                    System.out.println("在线用户如下");
                    for(int i = 0; i < onlineUsers.length; i++) {
                        System.out.println("用户： " + onlineUsers[i]);
                    }
                } else{
                    System.out.println("其他类型信息暂不处理");
                }

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
