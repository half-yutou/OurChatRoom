package org.example.client.ClientService;

import java.util.HashMap;

/*
管理客户端到服务端的线程的类
 */
public class ManageClientConnectServerThread {
    //把多个线程放入HashMap集合中，key就是用户ID，value就是线程
    private static HashMap<String, ClientConnectServerThread> hm = new HashMap<>();

    //将某个线程加入到集合
    public static void addClientConnectServerThread(String userID, ClientConnectServerThread clientConnectServerThread) {
        hm.put(userID,clientConnectServerThread);
    }

    //通过UserID将某个线程取出
    public static ClientConnectServerThread getClientConnectServerThread(String userID) {
       return  hm.get(userID);
    }
}
