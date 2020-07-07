package com.cinder.jvm.ref;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object o = new Object();  //默认为强引用
        WeakReference<Object> weakReference = new WeakReference<>(o);
        System.out.println(o);
        System.out.println(weakReference.get());
        o = null;
        System.gc();

        System.out.println(o);
        System.out.println(weakReference.get());
    }
}
