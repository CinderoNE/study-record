package com.cinder.netty.rpc.provider;

/**
 * @author Cinder
 * @Description:远程调用服务提供者
 * @Date create in 22:02 2020/7/9/009
 * @Modified By:
 */
public class Provider {
    public static void main(String[] args) {
        NettyServer.startServer(6670);
    }
}
