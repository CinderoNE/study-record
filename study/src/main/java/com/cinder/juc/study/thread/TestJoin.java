package com.cinder.juc.study.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Cinder
 * @Description:
 * @Date create in 1:56 2020/6/8/008
 * @Modified By:
 */
public class TestJoin {

    private final String str = new String("cinder");
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static AtomicInteger atomicInteger2 = new AtomicInteger(3);
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 10,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(10),
                r -> new Thread(r, "CINDER" + atomicInteger.incrementAndGet()));
        for (int i = 0; i < 10; i++) {
            TestJoin testJoin = new TestJoin();
            threadPoolExecutor.execute(testJoin::test);
        }
        threadPoolExecutor.shutdown();

    }

    public void test(){
        synchronized (this){
            System.out.println(atomicInteger2.get());
            if (atomicInteger2.decrementAndGet() == 0)
                System.out.println("finish");
        }
    }
}
