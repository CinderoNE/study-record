package com.cinder.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author Cinder
 * @Description:
 * @Date create in 19:10 2020/7/7/007
 * @Modified By:
 */
public class ClientOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("ClientOutHandler.write获得从前一个handler获得的数据：" + msg);
        ctx.writeAndFlush(msg);
    }


}
