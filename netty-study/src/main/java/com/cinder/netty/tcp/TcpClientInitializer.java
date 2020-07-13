package com.cinder.netty.tcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:32 2020/7/8/008
 * @Modified By:
 */
public class TcpClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(new TcpClientHandler());
    }
}
