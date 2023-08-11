package org.example.client;

import org.example.client.ClientService.ClientMessageService;
import org.example.client.ClientService.UserService;
import org.example.common.Message;

import java.io.IOException;
import java.util.Scanner;

/*
登陆界面
 */
public class LoginInterface {
    private boolean loop = true;//控制是否继续显示菜单
    private String key = " ";//获取用户输入
    private Scanner scan = new Scanner(System.in);
    private UserService userService = new UserService();
    private ClientMessageService messageService = new ClientMessageService();//用于消息发送等方法的调用
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

                    if(userService.checkUser(userID,userPwd)) {//此时需要去服务端验证是否存在该用户，以及密码正误 class UserValidate{}
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
                                case "1":
                                    userService.onlineUserList();
                                    break;
                                case "2":
                                    System.out.println("请输入群发内容:");
                                    String content = scan.nextLine();
                                    messageService.SendMessageToGroup(content, userID);
                                    break;
                                case "3":
                                    System.out.println("请输入聊天对象(在线):");
                                    String getterID = scan.nextLine();
                                    System.out.println("请输入内容:");
                                    content = scan.nextLine();
                                    //使用一个方法将信息发送给服务端
                                    messageService.SendMessageToOne(content, userID, getterID);
                                    break;
                                case "4":
                                    break;
                                case "9":
                                    //调用某个方法，给服务端发送一个退出系统的message
                                    //让服务端和该客户端断开链接，然后再安全退出该客户端
                                    userService.logout();
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
