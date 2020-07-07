package com.cinder.juc.study.aqs;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 模拟秒杀
 */
public class SecKill {

    public static void main(String[] args) {
        testSecKill(100);
    }


    /**
     *
     * @param count  参与秒杀人数
     */
    public static void testSecKill(int count){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(30, 30, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(100),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        SecKill secKill = new SecKill();
        for (int i = 0; i < count; i++) {
           threadPoolExecutor.execute(secKill::sekKill);
        }
        threadPoolExecutor.shutdown();
    }

    private CinderLock01 cinderLock = new CinderLock01();
    private int stock = 10; //库存
    /**
     * 秒杀产品
     * @return
     */
    public void sekKill(){
        cinderLock.lock();
        if (stock <= 0) {
            cinderLock.unlock();
            System.out.println("秒杀失败，已经没有库存了" + Thread.currentThread().getName());
            return ;
        }


        stock--;
        System.out.println("秒杀成功，剩余库存数：---->" + stock + Thread.currentThread().getName());
        cinderLock.unlock();
    }



}
