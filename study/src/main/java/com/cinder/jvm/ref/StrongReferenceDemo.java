package com.cinder.jvm.ref;

/**
 * 当内存不足，JVM开始垃圾回收，对于强引用的对象，就算是出现了OOM也不会对该对象进行回收
 * ，死都不收。
 *
 * 强引用是最常见的普通对象引用，只要还有前饮用指向一个对象，就能表明对象还“活着”，垃圾收集器不会碰到这种对象。
 * 在Java中最常见的就是强引用，把一个对象赋给一个引用变量，这个引用变量就是一个强引用。
 * 当一个对象被强引用变量引用时，它处于可达状态，它是不可能被垃圾机制回收的，
 * 即使该对象以后永远都不能被用到，JVM也不会回收
 * 。因此强引用是造成Java内存泄露的主要原因之一。
 *
 * 对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显示地将相应（强）引用赋值为null，
 * 一般认为就是可以被垃圾收集的了（当然具体回收时还要看垃圾收集策略）。
 */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object o = new Object();  //默认为强引用
        Object o2 = o;
        o = null;
        System.gc();

        System.out.println(o2);
    }
}
