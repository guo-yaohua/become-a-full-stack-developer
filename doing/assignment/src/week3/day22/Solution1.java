package week3.day22;

import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        ThreadHello threadHello = new ThreadHello();
        ThreadStop threadStop = new ThreadStop();

        threadHello.start();
        threadStop.start();
    }
}

class ThreadHello extends Thread {
    @Override
    public void run() {
        try {
            while (!ThreadStop.stop) {
                System.out.println("你好！");
                Thread.sleep(3000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadStop extends Thread {
    static boolean stop = false;
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s.equals("stop")) {
            ThreadStop.stop = true;
        }
        scanner.close();
    }
}
