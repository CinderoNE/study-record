package com.cinder.juc.study.ProdConsumer;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShareResource2{
    private volatile boolean flag = true; //默认开启 进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public ShareResource2(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    //生产者
    public void prod() throws InterruptedException {
        String data = null;
        boolean offer;
        while (flag){
            data = atomicInteger.incrementAndGet()+"";
            offer = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if(offer){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列："+data+"成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列："+data+"失败");
            }
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"\t main叫停了，flag=false，生产取消");
    }

    public void consumer() throws InterruptedException {
        while (flag){
            String result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(result == null||result.equals("")){
                // flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 超过2秒没有消费，消费退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费"+result+"成功");
        }
    }

    public void stop(){
        flag = false;
    }
}

/**
 * volatie/CAS/atomicInteger/BlockQueue/线程交互/原子引用
 */
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {

        ShareResource2 shareResource2 = new ShareResource2(new ArrayBlockingQueue<>(10));

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 线程启动");
            try {
                shareResource2.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"生产者").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t 线程启动");
            try {
                shareResource2.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费者").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shareResource2.stop();
        System.out.println(Thread.currentThread().getName()+"\t 叫停了");
    }
}
