package org.example.server.ServerService;

import org.example.common.Message;
import org.example.common.MessageType;
import org.example.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/*
服务器端口监听端口9999，等待客户端链接并保持通信
 */
public class infoServer {
    //HashMap储存合法用户
    private static HashMap<String, User> validUsers = new HashMap<>();

    static {//在静态代码块初始化信息
        validUsers.put("001", new User("001", "123456"));
        validUsers.put("002", new User("002", "123456"));
        validUsers.put("003", new User("003", "123456"));
    }

    private boolean checkUser(String userID, String password) {
        User user = validUsers.get(userID);
        if(user == null) {
            System.out.println("用户不存在");
            return false;
        }
        if(!user.getPassword().equals(password)) {
            System.out.println("密码错误");
            return false;
        }
        return true;
    }

    private ServerSocket serverSocket = null;
    public infoServer() throws IOException {
        //端口可以写在配置文件中
        System.out.println("服务端正在监听9999端口");
        try {
            serverSocket = new ServerSocket(9999);

            //监听工作是一直进行了，不是链接一个客户端就退出
            while(true) {
                Socket socket = serverSocket.accept();
                //得到关联的输入流
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                User u = (User) objectInputStream.readObject();//读取客户端发送的user对象



                //new 一个回复信息和关联输出流，根据登录是否成功回复不同信息
                Message message = new Message();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());


                if(checkUser(u.getName(), u.getPassword())) {//登陆成功
                    message.setType(MessageType.MESSAGE_LOGIN_SUCCEED);

                    //将message对象回复
                    objectOutputStream.writeObject(message);
                    //成功后要创建一个线程与客户端保持通信，该线程持有一个socket对象
                    ServerConnectClientThread serverConnectClientThread = new ServerConnectClientThread(socket, u.getName());
                    serverConnectClientThread.start();

                    //把该线程对象放入集合中管理
                    ManageServerConnectClientThread.addServerConnectClientThread(u.getName(), serverConnectClientThread);
                } else {//登录失败
                    message.setType(MessageType.MESSAGE_LOGIN_FAIL);
                    objectOutputStream.writeObject(message);
                    socket.close();

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            //如果服务端退出了while循环，说明服务端不再监听
            serverSocket.close();
        }
    }
}
