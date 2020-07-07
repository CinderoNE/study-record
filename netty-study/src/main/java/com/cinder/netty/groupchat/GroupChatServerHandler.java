package com.cinder.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import javax.lang.model.element.VariableElement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:12 2020/7/2/002
 * @Modified By:
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {


    /**
     * 管理所有channel
     * GlobalEventExecutor：全局事件执行器，单例
     */
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    /**
     * 连接建立，第一个执行
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.add(channel);
        //通知channelGroup中所有channel
        channelGroup.forEach(ch -> {
            if (ch != channel){
                ch.writeAndFlush(LocalDateTime.now().format(dateTimeFormatter) +
                        "【client" + channel.remoteAddress() + "】加入群聊");
            }
        });
    }

    /**
     * 断开连接，通知其他客户
     * 当前channel会自动从channelGroup中去掉
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush(LocalDateTime.now().format(dateTimeFormatter) +
                "【client" + channel.remoteAddress() + "】离开群聊");
        System.out.println("channelGroup.size() = " + channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "上线");

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + "下线");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        System.out.println(LocalDateTime.now().format(dateTimeFormatter) +
                "【client" + channel.remoteAddress() + "】：" + msg);

        //将消息发给其他客户端，不发送给自己

        channelGroup.forEach(ch -> {
            if(ch != channel){
                ch.writeAndFlush(LocalDateTime.now().format(dateTimeFormatter)
                         + "【client" + channel.remoteAddress() + "】:"+  msg);
            }else{
                ch.writeAndFlush(LocalDateTime.now().format(dateTimeFormatter)  + "已发送消息：" + msg);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
