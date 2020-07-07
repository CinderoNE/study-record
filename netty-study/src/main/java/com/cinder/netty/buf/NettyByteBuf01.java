package com.cinder.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:37 2020/7/2/002
 * @Modified By:
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        ByteBuf buffer = Unpooled.buffer(10);


        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }

        //不需要flip就可以读了
        //ByteBuf内部维护readerIndex和writerIndex
        //0-readerIndex 表示已经读取的区间
        //readerIndex-writerIndex 表示可以读的区间
        //writerIndex-capacity 表示可以写的区间
        while (buffer.isReadable()){
            System.out.println(buffer.readByte());
        }
    }
}
