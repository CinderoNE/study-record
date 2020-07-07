package com.cinder.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 写出数据到文件
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception{
        String s = "Cinder,go!";

        FileOutputStream fileOutputStream = new FileOutputStream("d://nio.txt");

        //获取通道
        FileChannel channel = fileOutputStream.getChannel();

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将数据放入buffer
        byteBuffer.put(s.getBytes());

        //将buffer flip(重置position、limit)
        byteBuffer.flip();

        //将buffer中数据写出
        channel.write(byteBuffer);

        //关闭流
        fileOutputStream.close();
    }
}
