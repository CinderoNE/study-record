package com.cinder.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.nio.channels.SocketChannel;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:29 2020/7/8/008
 * @Modified By:
 */
public class TcpClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] buffer = new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        System.out.println("客户端收到消息：" + new String(buffer, CharsetUtil.UTF_8));
        System.out.println("客户端收到消息次数" + ++count);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for (int i = 0; i < 3; i++) {
            ByteBuf byteBuf = Unpooled.copiedBuffer("cinder" + i, CharsetUtil.UTF_8);
            ctx.writeAndFlush(byteBuf);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
