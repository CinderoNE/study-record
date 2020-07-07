package com.cinder.juc.study.lock;


public class CinderReentrantLock {

    private Thread currThread= null;
    private boolean isLocked = false;
    private int lockCount = 0;

    public synchronized void lock(){
        Thread thread = Thread.currentThread();
        while(isLocked && thread != currThread){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        currThread = thread;
        lockCount++;
    }

    public synchronized void unLock(){
        Thread thread = Thread.currentThread();
        if(thread == currThread){
            lockCount--;
            if(lockCount == 0){
                isLocked = false;
                currThread = null;
                notify();
            }
        }
    }
}
