package com.cinder.netty.rpc.provider;

import com.cinder.netty.rpc.common.MessageService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 * @author Cinder
 * @Description:
 * @Date create in 22:36 2020/7/9/009
 * @Modified By:
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter{


    private static MessageService messageService = new MessageServiceImpl();


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("NettyServerHandler.channelRegistered");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String mes = (String) msg;
        System.out.println("服务器收到协议msg：" + mes);
        //规定传输过来的msg要以cinder#开头
        if(mes.startsWith(MessageService.PROTO_PREFIX)){
            //匹配前缀，截取字符串（解码）,并调用对应方法
            String realMsg = mes.substring(mes.indexOf('#')+1);
            ctx.writeAndFlush(messageService.send(realMsg));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }
}
