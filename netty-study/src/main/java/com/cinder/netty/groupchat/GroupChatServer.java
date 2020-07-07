package com.cinder.netty.groupchat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author Cinder
 * @Description:
 * @Date create in 17:49 2020/7/2/002
 * @Modified By:
 */
public class GroupChatServer {
    private int port;


    public GroupChatServer(int port) {
        this.port = port;

    }

    public void run() throws InterruptedException {
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boosGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                     // ChannelOption.SO_BACKLOG对应的是tcp/ip协议listen函数中的backlog参数，
                    // 函数listen(int socketfd,int backlog)用来初始化服务端可连接队列，
                    // 服务端处理客户端连接请求是顺序处理的，所以同一时间只能处理一个客户端连接，
                    // 多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，
                    // backlog参数指定了队列的大小
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //加入解码器
                            ch.pipeline().addLast(new StringDecoder());
                            //加入编码器
                            ch.pipeline().addLast(new StringEncoder());
                            //业务处理handler
                            ch.pipeline().addLast(new GroupChatServerHandler());
                        }
                    });
            //同步启动服务器
            ChannelFuture channelFuture = serverBootstrap.bind(port).addListener(future -> {
                System.out.println("netty服务器启动");
            }).sync();

            //通道关闭
            channelFuture.channel().closeFuture().addListener(future -> {
                System.out.println("server shutdown");
            }).sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatServer(6670).run();
    }
}
