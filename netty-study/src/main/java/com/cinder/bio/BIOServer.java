package com.cinder.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动");
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        while (true) {
            System.out.println("等待连接");
           final Socket socket = serverSocket.accept();
            System.out.println("一个客户端连接");
            threadPool.execute(() -> {
                handler(socket);
            });
        }

    }

    public static void handler(Socket socket){
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()){
            byte[] bytes = new byte[1024];
            int length;
            while((length=inputStream.read(bytes)) != -1){
                System.out.println(new String(bytes, 0, length)+"线程："+Thread.currentThread().getName());
                outputStream.write(bytes,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
