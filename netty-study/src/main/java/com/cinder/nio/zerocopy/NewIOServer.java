package com.cinder.nio.zerocopy;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:28 2020/6/30/030
 * @Modified By:
 */
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));

        ByteBuffer byteBuffer = ByteBuffer.allocate(8096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            int read = 0;

            try {
                while ((read = socketChannel.read(byteBuffer)) != -1){
                    //重置position为0
                    byteBuffer.rewind();

                }
            } catch (IOException e) {
                System.out.println(socketChannel.getRemoteAddress() + "下线");
            }
        }


    }
}
