package com.cinder.netty.http;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http2.Http2ChannelDuplexHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:30 2020/7/1/001
 * @Modified By:
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {

        //加入netty提供的HttpServerCodec codec-> code decode
        //它时Http的编码和解码器
        ch.pipeline().addLast("CinderHttpServerCodec",new HttpServerCodec());
        ch.pipeline().addLast("CinderTestHttpServerHandler",new TestHttpServerHandler());

    }
}
