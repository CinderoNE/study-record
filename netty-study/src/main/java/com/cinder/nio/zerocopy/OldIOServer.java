package com.cinder.nio.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Cinder
 * @Description:
 * @Date create in 15:56 2020/6/30/030
 * @Modified By:
 */
public class OldIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                while (true) {
                    byte[] buffer = new byte[8096];
                    int read = dataInputStream.read(buffer);
                    if (read == -1){
                        break;
                    }
                }
            }catch (Exception e){
                System.out.println(socket.getRemoteSocketAddress() + "下线");
            }
        }
    }
}
