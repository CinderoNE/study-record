package com.cinder.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author Cinder
 * @Description:
 * @Date create in 22:01 2020/7/7/007
 * @Modified By:
 */
public class ClientHandler extends SimpleChannelInboundHandler<Long> {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ClientHandler.channelActive.writeToServer:13");
        ctx.writeAndFlush("13");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long msg) throws Exception {
        System.out.println("from server：" + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
