package com.cinder.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author Cinder
 * @Description:解码
 * @Date create in 22:12 2020/7/8/008
 * @Modified By:
 */
public class ProtocolMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        ProtocolMessage protocolMessage = new ProtocolMessage(length, content);
        out.add(protocolMessage);
    }
}
