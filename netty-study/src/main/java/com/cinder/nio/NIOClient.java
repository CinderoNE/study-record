package com.cinder.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static final String CINDER_NB = "cinder nb!";

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        if (!socketChannel.connect(inetSocketAddress)){

            while (!socketChannel.finishConnect()){
                System.out.println("还未完成连接，可以做其他事情");
            }
        }

        ByteBuffer byteBuffer = ByteBuffer.wrap(CINDER_NB.getBytes());

        socketChannel.write(byteBuffer);

        // socketChannel.close();

        System.in.read();
    }
}
