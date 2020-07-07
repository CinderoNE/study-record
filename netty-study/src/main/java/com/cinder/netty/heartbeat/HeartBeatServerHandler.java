package com.cinder.netty.heartbeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * @author Cinder
 * @Description:
 * @Date create in 18:20 2020/7/3/003
 * @Modified By:
 */
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {

    /**
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            IdleState state = event.state();
            String eventType = "";
            switch (state){
                case READER_IDLE:eventType = "读空闲";break;
                case WRITER_IDLE:eventType = "写空闲";break;
                case ALL_IDLE:eventType = "读写空闲";break;
                default:break;
            }
            Channel channel = ctx.channel();
            System.out.println(channel.remoteAddress() + "发生超时事件--->" + eventType);

            //发生空闲可以关闭通道
            ctx.close();
        }
    }
}
