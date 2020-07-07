package com.cinder.juc.study.BlockingQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue:不存储元素的阻塞队列，也即单个元素的队列。
 *
 * SynchronousQueue没有容量。
 *
 * 与其他BlockingQueue不同，SynchronousQueue是一个不存储元素的BlockingQueue。
 * 每一个put操作必须要等待一个take操作，否则不能继续添加元素，反之亦然。
 *
 * offer()  如果另一个线程正在等待接收它,将指定的元素插入此队列，
 * put()  将指定的元素添加到此队列,等待（如有必要）另一个线程接收它
 *
 * poll() 如果另一个线程当前正在使元素可用，则检索并删除此队列的头部。
 * take() 等待（如有必要）另一个线程将其插入,检索并删除此队列的头
 *
 * offer()+take()
 * put()+poll()
 * put()+take()
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> synchronousQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"\t offer");
                    synchronousQueue.offer("com/cinder");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                System.out.println(Thread.currentThread().getName()+"\t put");
                synchronousQueue.put("com/cinder");

            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(() -> {
            try {
                System.out.println(synchronousQueue.take());
                System.out.println(Thread.currentThread().getName()+"\t take");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(synchronousQueue.poll());
                System.out.println(Thread.currentThread().getName()+"\t poll");
            } catch (Exception e) {
                e.printStackTrace();
            }


        },"t2").start();

    }
}
