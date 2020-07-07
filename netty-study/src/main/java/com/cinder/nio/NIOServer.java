package com.cinder.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {
    public static void main(String[] args) throws IOException {




        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //创建一个selector
        Selector selector = Selector.open();

        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //注册到selector,关系OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("注册到selector上的通道有" + selector.keys().size());

        while (true){

            //等待一秒看有没有事件发生
            if(selector.select(1000) == 0){
                System.out.println("服务器等待一秒，无事件发生");
                continue;
            }

            //如果有事件发生,获取SelectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                //有连接事件
                if(selectionKey.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    System.out.println("客户端连接成功"+socketChannel.hashCode());
                    socketChannel.configureBlocking(false);
                    //注册到selector，关联一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("注册到selector上的通道有" + selector.keys().size());
                }

                //有OP_READ事件
                if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    int read = socketChannel.read(buffer);
                    if(read < 0){
                        System.out.println("客户端准备断开连接");
                        socketChannel.close();
                        selectionKey.cancel();
                        break;
                    }
                    System.out.println("客户端发送数据" + new String(buffer.array()));
                }

                //移除key防止重复操作
                keyIterator.remove();
            }

        }
    }

}
