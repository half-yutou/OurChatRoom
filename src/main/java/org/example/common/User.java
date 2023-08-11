package org.example.common;

import java.io.Serializable;

/*
表示一个用户信息
 */
public class User implements Serializable {//序列化才能实现传输功能
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User () {
        //无参构造器
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
