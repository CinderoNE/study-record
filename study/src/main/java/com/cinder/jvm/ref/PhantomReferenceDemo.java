package com.cinder.jvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;


/**
 * 虚引用需要java.lang.refPhantonReference类来实现。
 *
 * 顾名思义，就是形同虚设，与其他几种引用都不同，虚引用并不会决定对象的生命周期。
 * 如果一个对象仅持有虚引用，那么它就和没有任何引用一样，在任何时候都可能被垃圾回收器回收
 * ，它不能单独使用也不能通过它访问对象，虚引用必须和队列（ReferenceQueue）联合使用。
 *
 * 虚引用的主要作用是跟踪对象被垃圾回收的状态。仅仅是提供了一种确保对象被finalize以后，做某些事情的机制。
 *
 * PhantomReference的get方法总是返回null，因此无法访问对应的引用对象。
 * 其意义在于说明一个对象那个已经进入finalization阶段，可以被gc回收，用来实现比finalization机制更灵活的回收操作。
 *
 * 换句话说，
 * 设置虚引用关联的唯一目的，就是在这个对象被收集器回收的时候收到一个系统通知或者后续添加进一步的处理
 * 。
 * Java技术允许使用finalize（）方法在垃圾收集器将对象从内存中清楚之前做必要的清理工作。
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference= new PhantomReference<Object>(o,referenceQueue);

        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        o = null;
        System.gc();

        Thread.sleep(500);

        System.out.println("===========================");
        System.out.println(o);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
