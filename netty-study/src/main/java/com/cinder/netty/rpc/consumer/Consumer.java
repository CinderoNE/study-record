package com.cinder.netty.rpc.consumer;

import com.cinder.netty.rpc.common.MessageService;

/**
 * @author Cinder
 * @Description:
 * @Date create in 1:10 2020/7/10/010
 * @Modified By:
 */
public class Consumer {
    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient();
        MessageService messageService =
                (MessageService) nettyClient.getProxy(MessageService.class, MessageService.PROTO_PREFIX);
        for (int i = 0; i < 10; i++) {
            String msg = messageService.send("simple dubbo");
            System.out.println("远程调用结果："+msg);
        }



    }
}
