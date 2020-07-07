package com.cinder.clinit_init;

public interface Clindet {
    String CINDER = "com/cinder";

    default void m1(){
        System.out.println("Clindet.m1");
    }

    static void m2(){
        System.out.println("Clindet.m2");
    }
}
