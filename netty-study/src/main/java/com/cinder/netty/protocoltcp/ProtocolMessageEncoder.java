package com.cinder.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Cinder
 * @Description:
 * @Date create in 22:24 2020/7/8/008
 * @Modified By:
 */
public class ProtocolMessageEncoder extends MessageToByteEncoder<ProtocolMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, ProtocolMessage msg, ByteBuf out) throws Exception {
        System.out.println("ProtocolMessageEncoder.encode");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());
    }
}
