package com.cinder.juc.study.lock;

import java.util.concurrent.TimeUnit;

/**
 * 可重入锁（也就是递归锁）：指的是同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 *
 * 也就是说，
 * 线程可以进入任何一个它已经拥有的锁所有同步着的代码块。
 */

class Phone{
    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()+"\t Phone.sendSMS");
        sendEmail();
    }
    public synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+"\t Phone.sendEmail");
    }

    private CinderReentrantLock lock = new CinderReentrantLock();

    public void method1(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"\t Phone.method1");
        method2();
        lock.unLock();
    }

    public void method2(){
        lock.lock();
        System.out.println(Thread.currentThread().getName()+"\t Phone.method2");
        lock.unLock();
    }

}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        System.out.println("Synchronized 可重入锁测试");
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSMS();
        },"t1").start();

        new Thread(() -> {
            phone.sendSMS();
        },"t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ReenterLock 可重入锁测试");

        new Thread(() -> {
            phone.method1();
        },"t3").start();

        new Thread(() -> {
            phone.method1();
        },"t4").start();

    }
}
