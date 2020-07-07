package com.cinder.netty.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author Cinder
 * @Description:
 * @Date create in 14:01 2020/7/1/001
 * @Modified By:
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        //创建两个线程组
        //boosGroup只处理连接请求，业务处理交给workerGroup
        //boosGroup和workerGroup含有子线程数（NioEventLoop）默认为cpu核心数*2
        EventLoopGroup boosGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //服务端启动器
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(boosGroup,workerGroup)
                    //使用NioServerSocketChannel作为服务器的通道实现
                    .channel(NioServerSocketChannel.class)
                    //设置线程队列得到的连接个数
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置保持活动连接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //给workerGroup的EventLoop对应的管道设计处理器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //这里可以将每个socketChannel保存进集合，根据用户的标识，确定channel引用，然后用write方法
                            //向客户端推送信息，最终write会提交到任务队列中被异步消费
                            System.out.println("socketChannel.hashCode() = " + socketChannel.hashCode());
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            //绑定端口并同步启动服务器
            ChannelFuture cf = serverBootstrap.bind(6666).sync();
            // 监听绑定事件，如果为sync操作，可以直接从ChannelFuture获取结果
            // if (cf.isSuccess()){
            //     System.out.println("监听端口6666成功");
            // }
            cf.addListener((ChannelFutureListener) future -> {
                if (cf.isSuccess()){
                    System.out.println("监听端口6666成功");
                }else {
                    System.out.println("监听端口6666失败");
                    System.out.println("失败原因:" + cf.cause().getMessage());
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        } finally {
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }



    }
}
