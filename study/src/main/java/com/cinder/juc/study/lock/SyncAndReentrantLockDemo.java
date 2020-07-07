package com.cinder.juc.study.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目:多线程之间按顺序调用，实现A->B->C三 个线程启动，要求如下:
 * AA打15次，BB打印10次, CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次， CC打印15次
 * ...
 * 夹10轮
 *
 */
class ShareResource{
    private int flag = 1; //1-A 2-B 3-C
    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public void print5(int rounds) throws InterruptedException {
        lock.lock();
        try{
            //判断
            while (flag != 1){
                conditionA.await();
            }
            //工作
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t rounds:"+rounds+"\t"+i);
            }
            //唤醒B
            flag = 2;
            conditionB.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void print10(int rounds) throws InterruptedException {
        lock.lock();
        try{
            //判断
            while (flag != 2){
                conditionB.await();
            }
            //工作
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t rounds:"+rounds+"\t"+i);
            }
            //唤醒C
            flag = 3;
            conditionC.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
    public void print15(int rounds) throws InterruptedException {
        lock.lock();
        try{
            //判断
            while (flag != 3){
                conditionC.await();
            }
            //工作
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t rounds:"+rounds+"\t"+i);
            }
            //唤醒A
            flag = 1;
            conditionA.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }



}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource sr = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                try {
                    sr.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();



    }
}
