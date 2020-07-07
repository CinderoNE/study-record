package com.cinder.singleton.test;

import com.cinder.singleton.lazy.Singleton04;

import java.util.concurrent.*;

public class TestSingleton04 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        Callable<Singleton04> c = new Callable<Singleton04>() {
            @Override
            public Singleton04 call() throws Exception {
                return Singleton04.getInstance();
            }
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Singleton04> f1 = executorService.submit(c);
        Future<Singleton04> f2 = executorService.submit(c);

        Singleton04 s1 = f1.get();
        Singleton04 s2 = f2.get();

        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println(s1.equals(s2));

        executorService.shutdown();



    }
}
