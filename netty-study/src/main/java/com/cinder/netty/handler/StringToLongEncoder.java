package com.cinder.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:06 2020/7/7/007
 * @Modified By:
 */
public class StringToLongEncoder extends MessageToByteEncoder<String> {
    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        System.out.println("StringToLongEncoder.encode");
        out.writeLong(Long.parseLong(msg));
    }
}
