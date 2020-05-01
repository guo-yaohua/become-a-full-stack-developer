package com.gyh.test;

public class Demo1 {

    public static void main(String[] args) {
        SalesTask salesTask = new SalesTask();
        Thread window1 = new Thread(salesTask, "窗口1");
        Thread window2 = new Thread(salesTask, "窗口2");
        Thread window3 = new Thread(salesTask, "窗口3");

        window1.start();
        window2.start();
        window3.start();
    }

}
class SalesTask implements Runnable {
    int tickets = 10;
    int i;

    private final Object lock = new Object();

    @Override
    public void run() {
        while (tickets > 0) {
            try {   // 增加售票延迟
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            i++;
            synchronized (lock) {
                if (tickets > 0) {
                    System.out.println(Thread.currentThread().getName() + " 卖出第 " + tickets-- + " 张票");
                }
            }
        }
    }
}

