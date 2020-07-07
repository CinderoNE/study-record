package com.cinder.singleton.lazy;

/**
 * 懒汉式(双重检查锁)
 */
public class Singleton04 {
    private Singleton04(){}



    private volatile static Singleton04 instance;

    public static Singleton04 getInstance(){
        if(instance == null){
            synchronized(Singleton04.class){
                if(instance == null)
                    instance = new Singleton04();
            }
        }
        return instance;
    }
}

