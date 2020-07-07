package com.cinder.juc.study.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Cinder
 * @Description
 * @Date create in 18:05 2020/5/12/012
 * @Modified By:
 */
public class ThreadLocalTest {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set("father cinder threadLocal");
        inheritableThreadLocal.set("father cinder inheritableThreadLocal");
        new Thread(() -> {
            System.out.println(threadLocal.get());
            threadLocal.set("son cinder threadLocal");
            System.out.println(inheritableThreadLocal.get());
            inheritableThreadLocal.set("son cinder inheritableThreadLocal");
            System.out.println(inheritableThreadLocal.get());
        }).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadLocal.get());
        System.out.println(inheritableThreadLocal.get());
        threadLocal.remove();
    }
}
