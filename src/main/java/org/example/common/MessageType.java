package org.example.common;

public interface MessageType {
    //不同常量的值表示不同的消息类型
    String MESSAGE_LOGIN_SUCCEED = "1";
    String MESSAGE_LOGIN_FAIL = "0";

    String MESSAGE_COMMON_MES = "3";
    String MESSAGE_GET_ONLINE_USER = "4";//要求返回在线用户信息
    String MESSAGE_RETURN_ONLINE_USER = "5";//返回在线用户信息

    String MESSAGE_CLIENT_EXIT = "6";//客户端请求退出

}
