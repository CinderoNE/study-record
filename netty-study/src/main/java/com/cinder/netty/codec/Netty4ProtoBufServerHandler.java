package com.cinder.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Cinder
 * @Description:
 * @Date create in 1:29 2020/7/7/007
 * @Modified By:
 */
public class Netty4ProtoBufServerHandler extends SimpleChannelInboundHandler<StudentOuter.Student> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentOuter.Student msg) throws Exception {
        //读取StudentOuter.Student
        System.out.println("Netty4ProtoBufServerHandler.channelRead0");
        System.out.println("服务端收到 = " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        StudentOuter.Student student = StudentOuter.Student.newBuilder().setId(1).setName("server cinder").build();
        ctx.writeAndFlush(student);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
