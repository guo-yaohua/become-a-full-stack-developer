package com.gyh.test;

interface Print {  // 定义一个接口
    public void print();
}

public class TestDemo {

    public static void main(String[] args) {
        new Print() {
            @Override
            public void print() {
                System.out.println("HelloWorld");
            }
        }.print();
    }
}

