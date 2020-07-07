package com.cinder.nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 从文件读取数据，输出到控制台
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception{
        //创建输入流
        File file = new File("d://nio.txt");
        FileInputStream fileInputStream = new FileInputStream(file);

        //获取通道
        FileChannel channel = fileInputStream.getChannel();

        //创建buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //byteBuffer将数据读入
        channel.read(byteBuffer);


        //将buffer中数据输出
        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();

    }
}
