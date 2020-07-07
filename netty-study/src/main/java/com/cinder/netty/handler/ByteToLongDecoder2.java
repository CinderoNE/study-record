package com.cinder.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author Cinder
 * @Description: Void表示不需要状态管理
 * @Date create in 21:54 2020/7/7/007
 * @Modified By:
 */
public class ByteToLongDecoder2 extends ReplayingDecoder<Void> {


    /**
     * 无需再判断是否可读
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("ByteToLongDecoder2.decode");
        out.add(in.readLong());
    }
}
