package com.cinder.juc.study.thread;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


class myThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"call()");
        //运算三秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}

/**
 * 第三种获得多线程的方式
 */
public class CallableDeom {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new myThread());

        new Thread(futureTask,"t1").start();
        new Thread(futureTask,"t2").start();  //多个线程启动一个Callable回复用，不会重新计算

        while (!futureTask.isDone()){

        }
        Integer result = futureTask.get(); //要求获得Callable线程的计算结果，没有完成会造成阻塞，直到获得返回值
        System.out.println("result = " + result);
    }
}
