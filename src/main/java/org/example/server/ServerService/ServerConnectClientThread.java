package org.example.server.ServerService;

/*
该类生成的对象和某个客户端保持通信
 */


import org.example.client.ClientService.ManageClientConnectServerThread;
import org.example.common.Message;
import org.example.common.MessageType;

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
        System.out.println("服务端与用户" + userID + "保持通讯中");
        while(true) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) objectInputStream.readObject();

                //根据message类型做不同处理
                if(message.getType().equals(MessageType.MESSAGE_GET_ONLINE_USER)) {
                    //客户端需求在线用户类型
                    System.out.println(message.getSender() + "拉取了在线用户列表");
                    String onlineUser = ManageServerConnectClientThread.getOnlineUser();

                    //构建一个message对象，包括了onlineUser
                    Message onlineUserMessage = new Message();
                    onlineUserMessage.setType(MessageType.MESSAGE_RETURN_ONLINE_USER);
                    onlineUserMessage.setContent(onlineUser);
                    onlineUserMessage.setGetter(message.getSender());

                    //将message返回给客户端
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    objectOutputStream.writeObject(onlineUserMessage);

                } else if (message.getType().equals(MessageType.MESSAGE_CLIENT_EXIT)) {
                    System.out.println(message.getSender() + "退出了系统");
                    //将这个客户端对应的线程从管理线程的集合中移除
                    ManageServerConnectClientThread.removeServerConnectClientThread(message.getSender());
                    socket.close();//关闭链接
                    //退出run()方法，即退出线程
                    break;
                } else {
                    System.out.println("其他类型暂不处理");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
