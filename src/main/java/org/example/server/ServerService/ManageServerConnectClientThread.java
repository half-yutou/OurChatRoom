package org.example.server.ServerService;


import java.util.HashMap;

/*
用于管理服务端与客户端通讯的线程
 */
public class ManageServerConnectClientThread {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    //放入线程
    public static void addServerConnectClientThread(String userID, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userID,serverConnectClientThread);
    }

    //通过userID返回对应的线程
    public static ServerConnectClientThread getServerConnectClientThread(String userID) {
        return hm.get(userID);
    }

}
