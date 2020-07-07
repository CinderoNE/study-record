package com.cinder.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:54 2020/7/7/007
 * @Modified By:
 */
public class ByteToLongDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ByteToLongDecoder.decode");
        if(in.readableBytes() >= 8){
            out.add(in.readLong());
        }
    }
}
