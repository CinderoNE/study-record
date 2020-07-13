package com.cinder.netty.rpc.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Cinder
 * @Description:
 * @Date create in 0:10 2020/7/10/010
 * @Modified By:
 */
public class NettyClient {
    private ThreadPoolExecutor threadPoolExecutor;

    private static NettyClientHandler clientHandler;

    /**
     *
     * @param target 要生成代理的接口类型
     * @param protoPrefix 定义协议的前缀
     * @return
     */
    public Object getProxy(Class<?> target,String protoPrefix){
        System.out.println("NettyClient.getProxy");
        return Proxy.newProxyInstance(target.getClassLoader(),new Class<?>[]{target},
                (proxy, method, args) -> {
                     if (Objects.isNull(clientHandler)){
                        initClient();
                     }
                     clientHandler.setParam(protoPrefix+args[0]);
                     return threadPoolExecutor.submit(clientHandler).get();
                });
    }

    /**
     * 初始化客户端服务器
     */
    private  void initClient(){
        System.out.println("NettyClient.initClient");
        if(Objects.isNull(threadPoolExecutor)){
            initThreadPool();
        }
        clientHandler = new NettyClientHandler();
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new StringEncoder());
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(clientHandler);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect("localhost", 6670).sync();
            System.out.println("客户端已连接");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void initThreadPool(){
        threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,
                Runtime.getRuntime().availableProcessors()*2, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100),new MyThreadFactory());
    }

    private static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "cinderPool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}


