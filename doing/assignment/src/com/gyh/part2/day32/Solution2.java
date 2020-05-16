package com.gyh.part2.day32;

import java.util.Arrays;

public class Solution2 {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for (int i = m, j = 0; j < n; i++, j++) {   // 直接将 nums2 插在 nums1 后
            nums1[i] = nums2[j];
        }

        Arrays.sort(nums1, 0, m + n); // 从小到大排序

    }

    public static void arrPrint(int[] nums, int m) {
        System.out.print("[" + nums[0]);
        for (int i = 1; i < m; i++) {
            System.out.print(", " + nums[i]);
        }
        System.out.print("]" + "\n");
    }

    public static void main(String[] args) {
        int[] nums1 = new int[20];
        int[] nums2 = new int[10];

        int m = 10;
        for (int i = 0; i < m; i++) {
            nums1[i] = i * 5;
        }
        int n = 5;
        for (int i = 0; i < 5; i++) {
            nums2[i] = 5 + i * 3;
        }

        System.out.print("nums1：");
        arrPrint(nums1, m);
        System.out.print("nums2：");
        arrPrint(nums2, n);
        System.out.print("合并：");
        merge(nums1, m, nums2, n);
        arrPrint(nums1, m + n);
    }
}
