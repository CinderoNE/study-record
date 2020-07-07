package com.cinder.singleton.test;

import com.cinder.singleton.hunger.Singleton03;


public class TestSingleton03 {
    public static void main(String[] args) {
        Singleton03 instance = Singleton03.INSTANCE;
        System.out.println(instance);

    }
}
