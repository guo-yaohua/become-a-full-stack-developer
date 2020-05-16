package com.gyh.test;

public class TestDemo {
    public static int add(int... arr) {
        int sum = 0;
        for(int i : arr) sum += i;
        return sum;
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        int sum = add(a, b, c);
        System.out.println(sum);
    }
}


