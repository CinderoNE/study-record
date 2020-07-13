package com.cinder.netty.protocoltcp;

/**
 * @author Cinder
 * @Description: 传输的消息格式
 * @Date create in 22:09 2020/7/8/008
 * @Modified By:
 */
public class ProtocolMessage {
    private int length;
    private byte[] content;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ProtocolMessage(int length, byte[] content) {
        this.length = length;
        this.content = content;
    }

    public ProtocolMessage() {
    }
}
