package com.gyh.part2.day31;

import java.util.ArrayList;

public class Solution3 {
    public static void main(String[] args) {
        int max = 20, min = 1;

        ArrayList<Object> list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            int num = (int) (Math.random() * (max - min) + min);; // 生成 1 - 20 的随机数

            if (!list.contains(num)) {
                list.add(num);
            }
        }
        System.out.println(list);
    }
}
