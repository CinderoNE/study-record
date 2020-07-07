package com.cinder.netty.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:33 2020/7/7/007
 * @Modified By:
 */
public class OuterHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        StudentOuter.Student student = (StudentOuter.Student) msg;
        StudentOuter.Student build = student.toBuilder().setName(student.getName() + 123).build();
        System.out.println("OuterHandler.write");
        ctx.writeAndFlush(build);

    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        System.out.println("OuterHandler.flush");

    }
}
