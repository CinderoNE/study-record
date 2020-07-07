package com.cinder.juc.study.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


class Resource{
    private Lock lock = new ReentrantLock();
    private Condition conditionOdd = lock.newCondition();
    private Condition conditionEven = lock.newCondition();

    private  volatile int value = 100;

    public void printOdd(){
        lock.lock();
        try{
            while (value % 2 == 0){
                conditionOdd.await();
            }
            System.out.println(Thread.currentThread().getName()+"-"+value--);
            conditionEven.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void printEven(){
        lock.lock();
        try{
            while (value % 2 != 0){
                conditionEven.await();
            }
            System.out.println(Thread.currentThread().getName()+"-"+value--);

            conditionOdd.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

}

public class PrintOddAndEvenShu {

    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                resource.printOdd();
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                resource.printEven();
            }
        },"BB").start();
    }

}
