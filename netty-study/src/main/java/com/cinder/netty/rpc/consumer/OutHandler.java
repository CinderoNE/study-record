package com.cinder.netty.rpc.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author Cinder
 * @Description:
 * @Date create in 1:58 2020/7/10/010
 * @Modified By:
 */
public class OutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("OutHandler.write");
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println("OutHandler.flush");
    }
}
