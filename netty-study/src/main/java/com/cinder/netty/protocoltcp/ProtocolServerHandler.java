package com.cinder.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:16 2020/7/8/008
 * @Modified By:
 */
public class ProtocolServerHandler extends SimpleChannelInboundHandler<ProtocolMessage> {
    private int count;
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolMessage msg) throws Exception {
        byte[] content = msg.getContent();
        int length = msg.getLength();
        System.out.println("content = " + new String(content, CharsetUtil.UTF_8));
        System.out.println("length = " + length);
        System.out.println("收到消息次数："+ ++count);

        //回复消息
        byte[] responseContent = UUID.randomUUID().toString().replaceAll("-","").getBytes(CharsetUtil.UTF_8);
        int responseLength = responseContent.length;
        ProtocolMessage response = new ProtocolMessage(responseLength, responseContent);
        ctx.writeAndFlush(response);
    }
}
