package org.example.client.ClientService;

import org.example.common.Message;
import org.example.common.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;

/*
与发送消息相关的方法
 */
public class ClientMessageService {

    public void SendMessageToOne(String content, String senderID, String getterID) {
        //构建message
        Message message = new Message();
        message.setType(MessageType.MESSAGE_COMMON_MES);//普通的聊天消息
        message.setSender(senderID);
        message.setGetter(getterID);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());
        System.out.println(senderID + "对" + getterID  + "说" + content);

        //发送给服务端
        //拿到线程
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderID).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void SendMessageToGroup(String content,String senderID){
        //构建message
        Message message = new Message();
        message.setType(MessageType.MESSAGE_GROUP_MES);//普通的聊天消息
        message.setSender(senderID);
        message.setContent(content);
        message.setSendTime(new java.util.Date().toString());
        System.out.println("用户: "+senderID + " 群发了消息: " + content);
        //发送给服务端
        //拿到线程
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderID).getSocket().getOutputStream());
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
