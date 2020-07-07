package com.cinder.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author Cinder
 * @Description:nio群聊服务端
 * @Date create in 0:52 2020/6/30/030
 * @Modified By:
 */
public class GroupChatServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private static final int PORT = 6666;

    public GroupChatServer() {
        //初始化操作
        try {
            selector = Selector.open();

            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务端启动");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听是否有客户端连接
     */
    public void listen(){
        while (true){
            try {
                int select = selector.select(2000);
                if(select > 0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        //连接事件
                        if (key.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + "上线了");
                        }
                        //读事件
                        if (key.isReadable()){
                            //读客户端的信息;
                            readMsg(key);
                        }
                        iterator.remove();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        }
    }

    /**
     * 读事件处理方法
     * @param key
     */
    private void readMsg(SelectionKey key) {
        SocketChannel socketChannel = null;
        try{
            socketChannel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
            int read = socketChannel.read(byteBuffer);
            if(read > 0){
                String msg = new String(byteBuffer.array());
                System.out.println("客户端消息from" + msg);
                sendMsgToOthers(msg,socketChannel);
            }
        }catch (Exception e){
            try {
                if (socketChannel != null) {
                    System.out.println(socketChannel.getRemoteAddress() + "下线了");
                    key.cancel();
                    socketChannel.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * 从服务器转发消息
     * @param msg
     * @param self
     */
    private void sendMsgToOthers(String msg, SocketChannel self) throws IOException {
        for (SelectionKey key : selector.keys()) {
            SelectableChannel channel = key.channel();
            if (channel instanceof SocketChannel && self != channel){
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                //buffer数据写入通道
                ((SocketChannel) channel).write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
