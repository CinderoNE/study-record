package com.cinder.juc.study.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 第四种获得/使用java多线程的方式，线程池
 *
 * 线程池七大参数
 * 1.corePoolSize：线程池中的常驻核心线程数
 * 2.maximumPoolSize：线程池能够容纳同时执行的最大线程数，此值必须大于等于1.
 * 3.keepAliveTime：多余的空闲线程的存活时间。当前线程池数量超过corePoolSize时，
 * 当空闲时间达到keepAliveTime值时，多余空闲线程会被销毁直到只剩下corePoolSize个线程为止。
 * 4.unit: keepAliveTime的单位
 * 5.workQueue：任务队列，被提交但尚未被执行的任务。
 * 6.threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程一般用默认的即可。
 * 7.handler：拒绝策略，表示当队列满了并且工作线程大于等于线程池的最大线程数。
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        /*ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );


        try{
            for (int i = 0; i < 10; i++) {
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 执行业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            threadPoolExecutor.shutdown();
        }*/
        threadPoolInit();


    }

    public static void threadPoolInit() {
        ExecutorService es1 = Executors.newFixedThreadPool(5); //一池固定线程
        ExecutorService es2 = Executors.newSingleThreadExecutor();//一池一线线程
        ExecutorService es3 = Executors.newCachedThreadPool();//一池N线线程（可扩容）

        try{
            for (int i = 0; i < 100; i++) {
                es3.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 执行业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            es3.shutdown();
        }
    }
}
