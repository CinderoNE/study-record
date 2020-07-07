package com.cinder.juc.study.thread;


import java.util.concurrent.TimeUnit;

class HoldLock implements Runnable{
    private String lockA;
    private String lockB;


    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 获得"+lockA);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"\t 尝试获得"+lockB);
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 获得"+lockB);
            }
        }
    }
}

/**
 * 死锁是指两个或者两个以上的进程在执行过程中，因抢夺资源而造成的一种互相等待的现象
 * ，若无外力干涉它们将都无法推进下去，如果系统资源充足，进程的资源请求都能够得到满足，死锁出现的可能性也就很低，否则就会因争夺有限的资源而陷入死锁。
 *
 */
public class DeadLockDemo {
    public static void main(String[] args) {
       new Thread(new HoldLock("lockA","lockB"),"ThreadA").start();
       new Thread(new HoldLock("lockB","lockA"),"ThreadB").start();


        /**
         * linux ps -ef|grep xxxx  ls -l
         * windows下的java程序  也有类似ps查看进程的命令 jps-l
         * jstack 进程号  查看死锁
         */
    }
}
