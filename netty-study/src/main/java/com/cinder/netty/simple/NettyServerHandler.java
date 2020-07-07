package com.cinder.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author Cinder
 * @Description:
 * @Date create in 15:16 2020/7/1/001
 * @Modified By:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //用户自定义普通任务，加入到taskQueue中

        //进入任务队列,5秒后执行
        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端2！",CharsetUtil.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        //进入任务队列,前一个任务执行完后才执行(同一线程)
        ctx.channel().eventLoop().execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端3！",CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        //用户自定义定时任务，加入到scheduleTaskQueue中
        //taskQueue执行完后才会执行scheduleTaskQueue，但是任务计时是同时计算的
        ctx.channel().eventLoop().schedule(() -> {
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端！自定义定时任务1",CharsetUtil.UTF_8));
        },10,TimeUnit.SECONDS);
        ctx.channel().eventLoop().schedule(() -> {
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端！自定义定时任务2",CharsetUtil.UTF_8));
        },12,TimeUnit.SECONDS);



        System.out.println(Thread.currentThread().getName());
        System.out.println("ctx = " + ctx);
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println("客户端消息：" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址：" + ctx.channel().remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //将数据写入缓存并刷新
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端1！",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
