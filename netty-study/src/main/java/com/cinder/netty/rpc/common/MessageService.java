package com.cinder.netty.rpc.common;

/**
 * @author Cinder
 * @Description:公共接口
 * @Date create in 21:54 2020/7/9/009
 * @Modified By:
 */
public interface MessageService {

    String PROTO_PREFIX = "cinder#";
    /**
     * 发送消息
     * @param msg
     * @return
     */
    String send(String msg);
}
