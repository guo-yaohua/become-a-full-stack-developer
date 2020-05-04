package com.gyh.part1.day03;

import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        System.out.print("输入一个数字：");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();

        if ((num & (num - 1)) == 0) {
            System.out.println("该数是 2 的整数次幂");
        } else {
            System.out.println("该数不是 2 的整数次幂");
        }
    }
}
