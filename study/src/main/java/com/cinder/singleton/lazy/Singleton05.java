package com.cinder.singleton.lazy;

/**
 * 静态内部类（懒汉式）
 */
public class Singleton05 {

    private Singleton05(){}


    private static class Inner{
        final static Singleton05 INSTANCE = new Singleton05();
    }

    public static Singleton05 getInstance(){
        return Inner.INSTANCE;
    }
}
