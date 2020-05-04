package com.gyh.part1.day07;

import java.util.Scanner;

public class Solution4 {
    public static void main(String[] args) {
        System.out.print("天数（1 ~ 10）：");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        sc.close();

        System.out.printf("第 %d 天的桃子个数：%d \n", i, peach(11 - i));
    }

    public static int peach(int n) {
        if (n == 1) {
            return 1;
        }
        return (peach(n - 1) + 1) * 2;
    }
}
