package org.example.common;

/*
消息对象
 */

import java.io.Serializable;

public class Message implements Serializable {//实现序列化和反序列化
    private static final long serialVersionUID = 1L;
    //发送者,接收者,内容,形式和发送时间
    private String sender;
    private String getter;
    private String content;
    private String type;
    private String sendTime;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
