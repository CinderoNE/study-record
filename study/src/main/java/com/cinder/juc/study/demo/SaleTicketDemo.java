package com.cinder.juc.study.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo {

    private static AtomicInteger tickes = new AtomicInteger(30);
    private static  int A = 30;
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        for(int i=1; i<=3; i++)
        {
            new Thread(() -> {
                for (int j = 0; j < 30; j++) {
                    lock.lock();
                    try {
                        if(A>0){
                            System.out.println(Thread.currentThread().getName() +
                                    "售票员,出售一张票还剩下" + A--);
                        }
                        // if(tickes.get() > 0)
                        //     System.out.println(Thread.currentThread().getName() +
                        //             "售票员,出售一张票还剩下" + tickes.decrementAndGet());
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            },String.valueOf(i)).start();
        }
        People people = new People();
        new Thread(() -> {
            people.sayHello();
        },"A").start();
        new Thread(() -> {
            people.sayBye();
        },"B").start();


    }
}

class People{
    private String name;

    public static synchronized void sayHello(){
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("People.sayHello");
    }

    public synchronized void sayBye(){
        System.out.println("People.sayBye");
    }
}

