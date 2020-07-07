package com.cinder.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Cinder
 * @Description:netty心跳检测
 * @Date create in 17:02 2020/7/3/003
 * @Modified By:
 */
public class NettyHeartBeatServer {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //添加处理空闲状态处理器IdleStateHandler
                            //1.readerIdleTime 多长时间没有读，就会发送一个心跳包检测是否还处于连接
                            //2.writerIdleTime 多长时间没有写，就会发送一个心跳包检测是否还处于连接
                            //3.allIdleTime 多长时间没有读写，就会发送一个心跳包检测是否还处于连接
                            //4.Triggers an {@link IdleStateEvent} when a {@link Channel} has not performed
                            //  * read, write, or both operation for a while.（触发IdleStateEvent事件）
                            //5.当事件触发后，会传递给管道的下一个handler处理
                            //通过调用下一个handler的userEventTriggered
                            ch.pipeline().addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            ch.pipeline().addLast(new HeartBeatServerHandler());
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(6670).sync();
            channelFuture.channel().closeFuture().sync();
        }finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
