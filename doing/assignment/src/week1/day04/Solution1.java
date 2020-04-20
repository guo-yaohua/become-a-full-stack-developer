package week1.day04;

import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        System.out.print("输入一个 5 位数：");

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.close();

        if ((num % 10 == num / 10000) && ((num / 10) % 10 == (num / 1000) % 10)) {
            System.out.println("是回文数");
        } else {
            System.out.println("不是回文数");
        }
    }
}
