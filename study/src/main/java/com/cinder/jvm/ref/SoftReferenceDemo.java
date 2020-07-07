package com.cinder.jvm.ref;


import java.lang.ref.SoftReference;

public class SoftReferenceDemo {
    public static void main(String[] args) {
        memoryNotEnough();

    }

    public static void memoryEnough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        System.gc();

        System.out.println("#################");
        System.out.println(o1);
        System.out.println(softReference.get());
    }

    public static void memoryNotEnough() {
        //set -Xms5m -Xmx5m -Xlog:GC*
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o1);

        System.out.println(o1);
        System.out.println(softReference.get());

        o1 = null;
        try{
            byte[] bytes = new byte[30 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            System.out.println("================");
            System.out.println(o1);
            System.out.println(softReference.get());

        }




    }
}
