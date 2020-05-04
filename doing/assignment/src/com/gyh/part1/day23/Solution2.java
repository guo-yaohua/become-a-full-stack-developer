package com.gyh.part1.day23;

public class Solution2 {
    public static void main(String[] args) {
        MyThread1 mt1 = new MyThread1();
        MyThread2 mt2 = new MyThread2();

        new Thread(mt1, "线程 A").start();
        new Thread(mt2, "线程 B").start();
    }
}

class num {
    static int nums = 0;
    static Object lock = new Object();
}

class MyThread1 implements Runnable {
    @Override
    public void run() {
        while (num.nums < 100) {
            synchronized(num.lock) {
                if (num.nums < 100) {
                    if (num.nums % 2 != 0) {
                        try {
                            num.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 打印：" + num.nums++);
                        num.lock.notifyAll();
                    }
                }
            }
        }
    }
}

class MyThread2 implements Runnable {
    @Override
    public void run() {
        while (num.nums < 100) {
            synchronized(num.lock) {
                if (num.nums < 100) {
                    if (num.nums % 2 == 0) {
                        try {
                            num.lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " 打印：" + num.nums++);
                        num.lock.notifyAll();
                    }
                }
            }
        }
    }
}