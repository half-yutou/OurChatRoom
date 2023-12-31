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
import java.util.HashMap;
import java.util.Iterator;

public class ServerConnectClientThread extends Thread {

    private Socket socket;

    private String userID;//链接到服务端的ID

    public ServerConnectClientThread(Socket socket, String userID) {
        this.socket = socket;
        this.userID = userID;
    }

    public Socket getSocket() {
        return socket;
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
                } else if (message.getType().equals(MessageType.MESSAGE_COMMON_MES)) {
                    //根据message获取getterID，再得到对应线程即可
                    ServerConnectClientThread serverConnectClientThread = ManageServerConnectClientThread.getServerConnectClientThread(message.getGetter());
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    //得到getter的对应线程后，发送信息即可
                    objectOutputStream.writeObject(message);//如果用户不在线可以保存到数据库，但是代码比较复杂，先略去不表
                } else if (message.getType().equals(MessageType.MESSAGE_GROUP_MES)){
                    //遍历线程集合
                    HashMap<String, ServerConnectClientThread> hm = ManageServerConnectClientThread.getHm();
                    Iterator<String> iterator = hm.keySet().iterator();
                    while(iterator.hasNext()){
                        //取出在线用户ID
                        String onlineUserID = iterator.next();
                        if(!onlineUserID.equals(message.getSender())){
                            //转发除发送者外的在线用户
                            ObjectOutputStream objectOutputStream =
                                    new ObjectOutputStream(hm.get(onlineUserID).getSocket().getOutputStream());
                            objectOutputStream.writeObject(message);
                        }
                    }
                } else {
                    System.out.println("其他类型暂不处理");
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
