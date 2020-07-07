package com.cinder.juc.study.Atomic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
class User{
    private String name;
    private int gae;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User cinder = new User("com/cinder", 18);
        User bat = new User("bat", 20);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(cinder);

        System.out.println(atomicReference.compareAndSet(cinder, bat)+"\t current User: "+atomicReference.get().getName());
        System.out.println(atomicReference.compareAndSet(cinder, bat)+"\t current User: "+atomicReference.get().getName());

    }
}
