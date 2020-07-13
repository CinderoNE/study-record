package com.cinder.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:23 2020/7/8/008
 * @Modified By:
 */
public class ProtocolClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new ProtocolMessageEncoder());
        ch.pipeline().addLast(new ProtocolMessageDecoder());
        ch.pipeline().addLast(new ProtocolClientHandler());
    }
}
