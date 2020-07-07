package com.cinder.juc.study.Atomic;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


class MyData{
    volatile int num;

    public void setTo60(){
        this.num = 60;
    }

    public  void addPlusPlus(){
        num++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1 验证volatile的可见性
 *     1.1 加入int number=0，number变量之前根本没有添加volatile关键字修饰,没有可见性
 *     1.2 添加了volatile，可以解决可见性问题
 * 2 验证volatile不保证原子性
 *
 *     2.1 原子性是不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者分割。
 *     需要整体完成，要么同时成功，要么同时失败。
 *
 *     2.2 volatile不可以保证原子性演示
 *
 *     2.3 如何解决原子性
 *         *加sync
 *         *使用我们的JUC下AtomicInteger
 */
public class VolatieDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();

        for(int i=1; i<=20; i++)
       {
           new Thread(() -> {
               for (int j = 0; j < 1000; j++) {
                   myData.addPlusPlus();
                   myData.addAtomic();
               }
           },String.valueOf(i)).start();
       }

        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int type finally num value:"+myData.num);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type finally num value:"+myData.atomicInteger);

    }

    //volatile可以保证可见性，及时通知其他线程，主物理内存的值已被修改
    public static void volatileTest() {
        MyData myData = new MyData();
        new Thread(() -> {
           System.out.println(Thread.currentThread().getName()+"\t come in" );
           try {
               TimeUnit.SECONDS.sleep(1);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           myData.setTo60();
           System.out.println(Thread.currentThread().getName()+"\t updated myData numTo60");
       }, "com/cinder").start();

        while (myData.num != 60){

        }
        System.out.println(Thread.currentThread().getName()+"\t get the update");
    }


}
