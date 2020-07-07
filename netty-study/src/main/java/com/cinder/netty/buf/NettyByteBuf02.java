package com.cinder.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:37 2020/7/2/002
 * @Modified By:
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("cinder", CharsetUtil.UTF_8);
        if (byteBuf.hasArray()){
            System.out.println(new String(byteBuf.array(), StandardCharsets.UTF_8));
            System.out.println("byteBuf = " + byteBuf);
        }

        System.out.println("byteBuf.arrayOffset() = " + byteBuf.arrayOffset());
        System.out.println("byteBuf.readerIndex() = " + byteBuf.readerIndex());
        System.out.println("byteBuf.writerIndex() = " + byteBuf.writerIndex());
        System.out.println("byteBuf.capacity() = " + byteBuf.capacity());

        int readableBytes = byteBuf.readableBytes();
        System.out.println("readableBytes = " + readableBytes);
        byteBuf.readByte();
        System.out.println("byteBuf.arrayOffset() = " + byteBuf.arrayOffset());
        System.out.println("byteBuf.readerIndex() = " + byteBuf.readerIndex());


        CharSequence charSequence = byteBuf.readCharSequence(4, CharsetUtil.UTF_8);
        System.out.println("charSequence = " + charSequence);

    }
}
