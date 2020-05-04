package com.gyh.part1.day05;

import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%d * %d = %2d  ",i, j, i * j);
            }
            System.out.println("");
        }
    }
}
