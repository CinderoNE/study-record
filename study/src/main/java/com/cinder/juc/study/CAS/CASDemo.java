package com.cinder.juc.study.CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS:CompareAndSwap  比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        AtomicReference<User> atomicReference = new AtomicReference<>();

        User s1 = new User(1, "s");
        boolean s = atomicReference.compareAndSet(null, s1);
        System.out.println(s);
        boolean b = atomicReference.compareAndSet(s1, new User(2, "k"));
        System.out.println(b);
        User user = atomicReference.get();
        System.out.println(user);

        //main do something..

        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1));


    }
}


class User{
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
