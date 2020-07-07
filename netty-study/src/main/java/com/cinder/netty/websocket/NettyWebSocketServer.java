package com.cinder.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 20:22 2020/7/3/003
 * @Modified By:
 */
public class NettyWebSocketServer {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();


        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();


                            //websocket一开始由http协议建立连接
                            pipeline.addLast(new HttpServerCodec());
                            //对大型数据流的支持，以块的方式写
                            pipeline.addLast(new ChunkedWriteHandler());
                            //将多个块进行聚合
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            //处理websocket握手以及Frame（控制帧）
                            //可以参考WebSocketFrame下的子类
                            //核心功能是将Http请求升级为ws协议，保存长连接
                            //服务器返回101状态码表示协议切换成功（101 Switching Protocols）
                            pipeline.addLast(new WebSocketServerProtocolHandler("/cinder"));
                            //业务逻辑handler
                            pipeline.addLast(new NettyWebSocketServerHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(6670).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
