package com.gyh.part2.day29;

import java.util.Arrays;

public class Solution3 {
    public static void main(String[] args) {
        int[] digits = {9, 9, 9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;

            if (digits[i] != 0) return digits;  // 没有发生进位就直接返回
        }

        // 发生进位，数组需要扩容 1 位，且除首位为 1，其余各位为 0
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}