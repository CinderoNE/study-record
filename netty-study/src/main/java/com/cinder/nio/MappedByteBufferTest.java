package com.cinder.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * MappedByteBufferTest可以让文件直接在内存（堆外内存）中修改，操作系统不需要在拷贝一次
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("netty-study/src/1.txt","rw");

        FileChannel channel = randomAccessFile.getChannel();

        /**
         * 参数一：使用读写模式
         * 参数二：可以修改的起始位置
         * 参数三：映射到内存的大小
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0, (byte) '爽');
        mappedByteBuffer.put(3, (byte) 'b');

        randomAccessFile.close();
    }
}
