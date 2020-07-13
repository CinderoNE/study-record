package com.cinder.netty.rpc.provider;

import com.cinder.netty.rpc.common.MessageService;

import java.util.Objects;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:56 2020/7/9/009
 * @Modified By:
 */
public class MessageServiceImpl implements MessageService {

    /**
     * 供消费方远程调用
     * @param msg
     * @return
     */
    @Override
    public String send(String msg) {
        if(Objects.isNull(msg)){
            System.out.println("服务器收到空消息");
            return "服务器收到空消息,远程调用返回";
        }
        System.out.println("服务器收到消息:" + msg);
        return "服务器收到消息，远程调用返回收到的消息："+msg;

    }
}
