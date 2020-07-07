package com.cinder.jvm.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(o,referenceQueue);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        o = null;
        System.gc();
        Thread.sleep(500);
        System.out.println("==========================");
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll()); //被回收前需要被引用队列保存下

    }
}
