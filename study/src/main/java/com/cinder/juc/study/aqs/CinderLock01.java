package com.cinder.juc.study.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

public class CinderLock01 {

    /**
     * 当前锁状态
     */
    private volatile int state = 0;

    /**
     * 持有锁的线程
     */
    private Thread lockHolder;


    private ConcurrentLinkedQueue<Thread> waiterQueue = new ConcurrentLinkedQueue<>();

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }


    public boolean acquire(){
        Thread currentThread = Thread.currentThread();
        int c = getState();
        if (c == 0){
            if ((waiterQueue.size() == 0 || currentThread == waiterQueue.peek()) &&
                    compareAndSwapState(0,1)){
                setLockHolder(currentThread);
                return true;
            }
        } else if (currentThread == lockHolder){   //可重入锁
            int nextc = c + 1;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }

    public boolean release(){
        if(Thread.currentThread() != lockHolder){
            throw new RuntimeException("lockHolder is not current thread");
        }
        int c = getState() - 1;
        boolean free = false;
        if (c == 0){
            free = true;
            setLockHolder(null);
            setState(c);
            //唤醒队列中的第一个线程
            Thread first = waiterQueue.peek();
            if(first != null){
                LockSupport.unpark(first);
            }
        }
        return free;
    }

    public void lock(){
        if (acquire())
            return;

        //获取锁失败加入到等待队列
        Thread currentThread = Thread.currentThread();
        waiterQueue.add(currentThread);

        while (true){
            if (acquire()) {
                //第一个线程节点出队列
                waiterQueue.poll();
                return;
            }

            //阻塞当前线程(将当前从cpu缓存中刷入至内存中RSS（运行时状态段）)
            LockSupport.park(currentThread); //保存当前线程的引用

        }


    }

    public void unlock(){
        release();
    }


    private static final Unsafe unsafe =getUnsafe();

    private static Unsafe getUnsafe(){
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean compareAndSwapState(int except,int update){
        return unsafe.compareAndSwapInt(this,stateOffset,except,update);
    }

    private static final long stateOffset;
    static {
        try {
            stateOffset = unsafe.objectFieldOffset(CinderLock01.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
           throw new RuntimeException();
        }
    }
}
