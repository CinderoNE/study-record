package com.cinder.nio.zerocopy;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:32 2020/6/30/030
 * @Modified By:
 */
public class NewIOClient {
    private static final int EIGHT_M = 8*1024*1024;
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 6666));

        FileChannel fileChannel = new FileInputStream("netty-study/src/jdk-8u152-linux-x64.tar.gz").getChannel();

        long start = System.currentTimeMillis();

        // 0拷贝， 和bio做对比，没有经过 再拷贝到内存 buffer 少了一次拷贝
        //linux下一个transferTo就可以完成
        //windows下一次最多传输8m，需分段传输
        //transferTo使用零拷贝
        long leftSize = fileChannel.size();
        long lastPos = 0;
        long count = 0;
        while (leftSize / EIGHT_M > 0) {
            fileChannel.transferTo(lastPos, EIGHT_M, socketChannel);
            count += EIGHT_M;
            lastPos += EIGHT_M;
            leftSize -= EIGHT_M;
        }
        long transferTo = fileChannel.transferTo(lastPos, leftSize, socketChannel);
        count += transferTo;
        System.out.println("传输字节数 = " + count);
        System.out.println("花费时间：" + (System.currentTimeMillis() - start));

        fileChannel.close();
    }
}
