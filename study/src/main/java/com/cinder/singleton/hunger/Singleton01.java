package com.cinder.singleton.hunger;

/**
 * 饿汉式
 */
public class Singleton01 {
    private Singleton01(){}

    public final static Singleton01 INSTANCE = new Singleton01();


}
