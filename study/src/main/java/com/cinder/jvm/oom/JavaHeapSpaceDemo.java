package com.cinder.jvm.oom;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "com/cinder";
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            str += str+new Random(111121312).nextInt();
            str.intern();
        }



    }
}
