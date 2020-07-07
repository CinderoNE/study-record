package com.cinder.nio;

import java.nio.IntBuffer;

public class Basic {
    public static void main(String[] args) {
        IntBuffer intBuffer = IntBuffer.allocate(5);

        for (int i = 0; i < 5; i++) {
            intBuffer.put(i);
        }

        //反转读写
        intBuffer.flip();

        //设置当前位置
        intBuffer.position(1);
        //设置limit
        intBuffer.limit(5);
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }

}
