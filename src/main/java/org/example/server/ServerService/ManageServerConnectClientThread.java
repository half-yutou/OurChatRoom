package org.example.server.ServerService;


import java.util.HashMap;
import java.util.Iterator;

/*
用于管理服务端与客户端通讯的线程
 */
public class ManageServerConnectClientThread {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();
    //返回哈希表
    public static HashMap<String, ServerConnectClientThread> getHm(){
        return hm;
    }

    //放入线程
    public static void addServerConnectClientThread(String userID, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userID,serverConnectClientThread);
    }

    //移除线程
    public static void removeServerConnectClientThread(String userID) {
        hm.remove(userID);
    }

    //通过userID返回对应的线程
    public static ServerConnectClientThread getServerConnectClientThread(String userID) {
        return hm.get(userID);
    }


    //编写方法，返回在线用户列表
    public static String getOnlineUser() {
        //遍历集合，遍历hashmap的key
        Iterator<String> iterator = hm.keySet().iterator();
        String onlineUserList = "";
        while (iterator.hasNext()) {
            onlineUserList +=  iterator.next().toString() + " ";
        }
        return onlineUserList;
    }
}
