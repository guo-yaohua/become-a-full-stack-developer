package com.gyh.part1.day03;

import java.util.Scanner;

public class Solution3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("输入一个数字（int 类型）：");
        int num = sc.nextInt();
        num = (num >> 31) ^ ((num >> 31) + num);
        System.out.println("绝对值：" + num);
    }
}
