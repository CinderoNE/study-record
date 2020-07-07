package com.cinder.nio;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception{
        //输入流
        FileInputStream fileInputStream = new FileInputStream("netty-study/src/1.txt");

        //获取channel
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        //输出流
        FileOutputStream fileOutputStream = new FileOutputStream("netty-study/src/2.txt");

        //获取channel
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        //创建Buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //byteBuffer将数据读入
        while(inputStreamChannel.read(byteBuffer) != -1){
            //flip
            byteBuffer.flip();
            //将byteBuffer数据写出
            outputStreamChannel.write(byteBuffer);

            //复位
            byteBuffer.clear();
        }



        //关闭流
        fileInputStream.close();
        fileOutputStream.close();






    }
}
