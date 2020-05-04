package com.gyh.part1.day04;

public class Solution3 {
    public static void main(String[] args) {
        double money = 0;
        int day = 0;

        while (money < 100) {
            day++;
            money += 2.5;
            if (day % 5 == 0) {
                money -= 6;
            }
        }
        System.out.printf("至少经过 %d 天 \n", day);
    }
}
