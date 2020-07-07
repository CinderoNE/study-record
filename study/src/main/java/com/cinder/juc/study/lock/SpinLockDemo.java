package com.cinder.juc.study.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁：是指尝试获取锁的线程不会立即阻塞，而是采用循环的方式去尝试获取锁
 * ，这样的好处是减少线程上下切换的消耗，缺点是循环会消耗CPU。
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null,thread)){

        }
        System.out.println(thread.getName()+"\t came in");

    }

    public void unLock(){
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName()+"\t came out");
        atomicReference.compareAndSet(thread,null);

    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.lock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLockDemo.unLock();
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLockDemo.lock();
            spinLockDemo.unLock();
        },"t2").start();

    }
}
