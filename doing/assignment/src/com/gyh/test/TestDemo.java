package com.gyh.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestDemo {
    public static void main(String[] args) {
        MyThread mt = new MyThread();

        new Thread(mt, "票贩子 A").start();
        new Thread(mt, "票贩子 B").start();
        new Thread(mt, "票贩子 C").start();
    }

    private static void synchronizedBlock() {
        Object lock = new Object();
        synchronized (lock) {
        }
    }
}

class MyThread implements Runnable {
    private int ticket = 5;

    private Lock lock = new ReentrantLock();    // 创建锁对象

    @Override
    public void run() {
        while (ticket > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // //利用 Lock 锁机制，构造同步代码块
            lock.lock();
            try {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出第" + ticket-- + "张票");
                }
            } finally {
                lock.unlock();
            }
        }
    }
}