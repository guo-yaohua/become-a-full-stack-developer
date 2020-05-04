package com.gyh.part1.day23;

public class Solution1 {
    public static void main(String[] args) {
        MyThread mt = new MyThread();

        new Thread(mt, "线程 A").start();
        new Thread(mt, "线程 B").start();
        new Thread(mt, "线程 C").start();
    }
}

class MyThread implements Runnable {
    private int fileSize = 100;

    @Override
    public void run() {
        while (fileSize > 0) {
            this.download();
        }
    }

    public synchronized void download() {   // 同步方法
        if (this.fileSize > 0) {
            try {
                Thread.sleep(100);  // 休眠 1 s，模拟延迟
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " 下载了 1 M，剩余 " + --fileSize + " M");
        }
    }
}