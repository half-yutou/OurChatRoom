package org.example.client.Service;

import org.example.common.Message;
import org.example.common.MessageType;
import org.example.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/*
完成用户登陆验证和用户注册功能
 */
public class UserValidate {
    /*
    new一个user对象，方便调用其信息
     */
    private final User u = new User();
    private Socket socket;
    public boolean checkUser(String userID, String pwd) throws IOException, ClassNotFoundException {
        u.setName(userID);
        u.setPassword(pwd);

        //链接服务端，发送u对象
        socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(u);//发送u对象

        //从服务端读取返回的信息Message
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        Message ms = (Message) objectInputStream.readObject();

        if(ms.getType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){
            //成功后要创建一个和服务端保持通信的线程 -> 创建一个类 ClientConnectServerThread{}
            ClientConnectServerThread clientConnectServerThread = new ClientConnectServerThread(socket);
            //启动客户端线程
            clientConnectServerThread.start();
            //为了方便管理多个线程，我们将线程放入集合管理，此处选择的是HashMap
            ManageClientConnectServerThread.addClientConnectServerThread(userID, clientConnectServerThread);
            return true;
        }else {
            //登录失败，不能启动和服务器通讯的线程
            //但是要记得关闭socket
            socket.close();
            return false;
        }
    }


}
