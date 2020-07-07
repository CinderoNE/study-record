package com.cinder.netty.codec;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 1:41 2020/7/7/007
 * @Modified By:
 */
public class Netty4ProtoBufClientHandler extends SimpleChannelInboundHandler<StudentOuter.Student> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //发送student对象
        for (int i = 0; i < 3; i++) {
            StudentOuter.Student student = StudentOuter.Student.newBuilder().setId(18+i).setName("cinder"+i).build();
            System.out.println("Netty4ProtoBufClientHandler.channelActive");
            ChannelFuture channelFuture = ctx.channel().writeAndFlush(student);
            channelFuture.addListener(future -> {
                if (future.isSuccess()) {
                    System.out.println("send success");
                }
            });
        }



    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentOuter.Student msg) throws Exception {
        System.out.println("客户端收到 = " + msg);
    }


}
