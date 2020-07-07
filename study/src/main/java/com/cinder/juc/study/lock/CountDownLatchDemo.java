package com.cinder.juc.study.lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i=1; i<=6; i++)
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 国被消灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getMessage()).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 秦国统一");
    }

    public static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for(int i=1; i<=6; i++)
       {
           new Thread(() -> {
               System.out.println(Thread.currentThread().getName()+"\t 离开教室");
               countDownLatch.countDown();
           },String.valueOf(i)).start();
       }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t 班长离开,关门");
    }
}
