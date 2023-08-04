package org.example.client;

import org.example.client.ClientService.UserValidate;

import java.io.IOException;
import java.util.Scanner;

/*
登陆界面
 */
public class LoginInterface {
    private boolean loop = true;//控制是否继续显示菜单
    private String key = " ";//获取用户输入
    private Scanner scan = new Scanner(System.in);
    private UserValidate userValidate = new UserValidate();
    public void mainMenu() throws IOException, ClassNotFoundException {
        while(loop) {
            System.out.println("===================欢迎登录=================");
            System.out.println("\t\t1 登陆系统");
            System.out.println("\t\t9 退出系统");

            key = scan.nextLine();

            switch (key) {
                case "1" :
                    System.out.println("请输入账号");
                    String userID = scan.nextLine();
                    System.out.println("请输入密码");
                    String userPwd = scan.nextLine();

                    if(userValidate.checkUser(userID,userPwd)) {//此时需要去服务端验证是否存在该用户，以及密码正误 class UserValidate{}
                        System.out.println("========欢迎" + userID + "登录========");
                        //进入二级菜单
                        while(loop) {
                            System.out.println("======二级菜单:" + userID + "在线======");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");

                            key = scan.nextLine();
                            switch (key) {
                                case "1" :
                                    System.out.println("在线列表");
                                    break;
                                case "2" :
                                    System.out.println("群发消息");
                                    break;
                                case "3" :
                                    System.out.println("私聊消息");
                                    break;
                                case "4" :
                                    System.out.println("发送文件");
                                    break;
                                case "9" :
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("请输入正确选项");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登陆失败，请检查用户名和密码");
                    }
                    break;
                case "9" :
                    loop = false;
                    break;
            }

        }
    }
}
