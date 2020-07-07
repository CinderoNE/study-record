package com.cinder.nio.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:00 2020/6/30/030
 * @Modified By:
 */
public class OldIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",6666);
        FileInputStream inputStream = new FileInputStream("netty-study/src/jdk-8u152-linux-x64.tar.gz");

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[8096];
        long length = 0;
        long count = 0;

        long start = System.currentTimeMillis();
        while ((count = inputStream.read(buffer)) >= 0){
            length += count;
            dataOutputStream.write(buffer);
        }

        System.out.println("发送字节数 = " + length);
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

        inputStream.close();
        dataOutputStream.close();
    }
}
