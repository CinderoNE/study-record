package com.cinder.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author Cinder
 * @Description:客户端与服务端通讯的数据封装为HttpObject
 * @Date create in 21:29 2020/7/1/001
 * @Modified By:
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    public static final String FAVICON_ICO = "/favicon.ico";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            System.out.println("msg 类型：" + msg.getClass());
            System.out.println("客户端地址" + ctx.channel().remoteAddress());
            System.out.println("ctx.pipeline().hashCode() = " + ctx.pipeline().hashCode());
            System.out.println("this.hashCode() = " + this.hashCode());

            HttpRequest request = (HttpRequest) msg;
            System.out.println("request.uri() = " + request.uri());
            if(FAVICON_ICO.equals(request.uri())){
                //不做响应
                return;
            }

            //回复Http消息
            ByteBuf byteBuf = Unpooled.copiedBuffer("hello from 服务器", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            // response.headers().set(HttpHeaderNames.CONNECTION,"keep-alive");

            ctx.writeAndFlush(response);
        }
    }


}
