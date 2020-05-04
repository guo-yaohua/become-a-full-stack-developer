package com.gyh.part1.day07;

import java.util.Scanner;

public class Solution3 {
    public static void main(String[] args) {
        System.out.print("直线数目：");

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        System.out.printf("%d 条直线最多把平面分成 %d 部分\n", n, line(n));
    }

    public static int line(int n) {
        if (n == 1) {
            return 2;
        }
        return n + line(n - 1);
    }
}
