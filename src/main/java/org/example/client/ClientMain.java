package org.example.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {

        //1.链接对应地址(此处为本机)的9999端口，若链接成功，返回一个socket对象
        Socket socket = new Socket(InetAddress.getLocalHost(), 9999);
        System.out.println("客户端 成功链接到端口");

        //2.获取和socket对应的输出流对象
        OutputStream outputStream = socket.getOutputStream();

        //3.通过输出流对象，写入数据到数据通道
        System.out.println("正在向服务端发送数据");
        outputStream.write("Hello, server".getBytes());
        System.out.println("数据发送完毕！请前往服务端查看");

        //4.输出完成后，必须关闭流对象和socket
        outputStream.close();
        socket.close();
    }
}
