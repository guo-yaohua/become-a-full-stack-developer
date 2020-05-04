package com.gyh.part1.day04;

public class Solution6 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 2, 2, 1, 5};

        int i = 0;
        int j = 0;
        int num = 0;
        for (i = 0; i < arr.length; i++) {
            num = 0;
            for (j = 0; j < arr.length; j++) {
                if (arr[i] == arr[j] && i != j) {
                    num++;
                }
            }
            if (num == 0) { // 出现 1 次
                System.out.println(arr[i] + " ");
            }
        }
    }
}
