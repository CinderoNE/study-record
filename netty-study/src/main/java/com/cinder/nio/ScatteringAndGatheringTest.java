package com.cinder.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * Scattering：将数据写入到buffer时，可以采用buffer数组，依次写入  [分散]
 *  Gathering: 从buffer读取数据时，可以采用buffer数组，依次读
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //绑定端口到socket，并启动
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等待客户端连接（telnet）
        SocketChannel socketChannel = serverSocketChannel.accept();

        //假设客户端接受八个字节
        int messageLength = 8;
        while (true){
            int byteRead = 0;
            while(byteRead < messageLength){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                Arrays.stream(byteBuffers).map(byteBuffer ->
                    "position = " + byteBuffer.position() +
                            "limit = " + byteBuffer.limit()
                ).forEach(System.out::println);
            }

            //将每个buffer进行flip
            Arrays.stream(byteBuffers).forEach(ByteBuffer::flip);

            //将buffer中数据写出
            int byteWrite = 0;
            while(byteWrite < messageLength){
                long l = socketChannel.write(byteBuffers);
                byteWrite += l;
            }
            //将每个buffer clear
            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);
        }



    }
}
