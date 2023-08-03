package org.example.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        //1.创建 ServerSocket类的对象
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端运行中 等待客户端的链接");

        //2.未链接客户端时，accept()会阻塞程序进行
        //如果有客户端链接，就会返回一个Socket类的对象socket，程序继续
        Socket socket = serverSocket.accept();

        System.out.println("成功链接客户端！");
        System.out.println("从客户端接收的数据如下");

        //3.使用对应的InputStream对象读取管道中的数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int readLen = 0;
        while((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));
        }

        //关闭流和socket
        inputStream.close();
        socket.close();
        serverSocket.close();//这个也别忘了关闭
    }
}
