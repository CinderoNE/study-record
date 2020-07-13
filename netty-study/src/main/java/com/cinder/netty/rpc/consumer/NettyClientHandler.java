package com.cinder.netty.rpc.consumer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


import java.util.concurrent.Callable;
import java.util.concurrent.locks.LockSupport;

/**
 * @author Cinder
 * @Description:整体流程：1.channelActive->2.setParam->3.call->4.channelRead->call
 * @Date create in 23:26 2020/7/9/009
 * @Modified By:
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private ChannelHandlerContext context;
    /**
     * 返回的结果
     */
    private Object result;
    /**
     * 传入的参数
     */
    private Object param;
    /**
     * 当前调用call方法的线程
     */
    private Thread currentThread;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyClientHandler.channelActive");
        context = ctx;
    }

    /**
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public  void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("NettyClientHandler.channelRead");
        result = msg;
        LockSupport.unpark(currentThread);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }



    /**
     * 被代理对象调用，先发送数据到服务提供端，之后等待channelRead方法读取完数据后，唤醒并返回结果
     * @return
     * @throws Exception
     */
    @Override
    public  Object call() throws Exception {
        currentThread = Thread.currentThread();
        System.out.println("NettyClientHandler.call");
        context.channel().writeAndFlush(param).addListener(future -> {
           if(!future.isSuccess()){
               System.out.println("失败");
               System.out.println(future.cause());
           }
        });
        LockSupport.park();
        System.out.println("NettyClientHandler.call2");
        return result;
    }



    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }
}
