package com.cinder.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Cinder
 * @Description:
 * @Date create in 16:14 2020/7/1/001
 * @Modified By:
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    //通道就绪时触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ctx = " + ctx);
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer("hello server! from cinder", CharsetUtil.UTF_8));
    }

    //通道有读取事件时
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("服务器消息：" +  byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器地址：" + ctx.channel().remoteAddress());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
