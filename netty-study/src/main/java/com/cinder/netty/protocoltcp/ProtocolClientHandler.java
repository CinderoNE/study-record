package com.cinder.netty.protocoltcp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:26 2020/7/8/008
 * @Modified By:
 */
public class ProtocolClientHandler extends SimpleChannelInboundHandler<ProtocolMessage> {
    private static final String[] STRINGS = {"weak up","刘","爽爽"};
    private ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ProtocolMessage msg) throws Exception {
        int length = msg.getLength();
        byte[] content = msg.getContent();
        System.out.println("content = " + new String(content, CharsetUtil.UTF_8));
        System.out.println("length = " + length);
        Integer count = threadLocal.get();
        System.out.println("客户端收到消息次数：" + count++);
        threadLocal.set(count);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 3; i++) {
            byte[] content = (STRINGS[i%3]).getBytes(CharsetUtil.UTF_8);
            ProtocolMessage protocolMessage = new ProtocolMessage(content.length,content);
            ctx.writeAndFlush(protocolMessage);
        }
    }
}
