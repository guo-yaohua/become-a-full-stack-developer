package com.gyh.part2.day28;

import java.util.Arrays;

public class Solution3 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3, 4, 5};
        System.out.println("当前数组：" + Arrays.toString(nums));

        int len = removeElement(nums, 3);
        System.out.println("删除值为 3 的元素后，长度为：" + len + "\n当前数组：");
        for (int i = 0; i < len; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    public static int removeElement(int[] nums, int val) {
        int index = 0;  // 处理后的坐标

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[index] = nums[i];
                index++;
            }
        }

        return index;   // 当前长度
    }
}
