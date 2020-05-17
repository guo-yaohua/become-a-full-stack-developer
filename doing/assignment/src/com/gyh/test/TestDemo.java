package com.gyh.test;

public class TestDemo {
    public static void main(String[] args) {
        int i = testFinally();
        System.out.println("i = " + i); //i = 30

    }

    public static int testFinally() {
        int i = 10;
        try {
            i = 20;
            i = i / 0;
        } catch (Exception e) {
            i = 30;
            return i;
        } finally {
            i = 40;
        }
        i = 50;
        return i;
    }
}


