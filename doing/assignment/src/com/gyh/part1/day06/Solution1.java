package com.gyh.part1.day06;

public class Solution1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int target = 10;
        int[] res = add(nums, target);
        System.out.printf("%d + %d = %d, 其中 %d 的下标为 %d,  %d 的下标为 %d\n", nums[res[0]], nums[res[1]], target, nums[res[0]], res[0], nums[res[1]], res[1]);
    }

    public static int[] add(int[] nums, int n) {
        int[] res = new int[2];

        find:
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == n) {
                    res[0] = i;
                    res[1] = j;
                    break find;  // 只有一个对应答案
                }
            }
        }
        return res;
    }
}

