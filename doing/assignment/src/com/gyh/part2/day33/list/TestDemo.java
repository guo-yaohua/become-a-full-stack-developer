package com.gyh.part2.day33.list;

public class TestDemo {

    public static void main(String[] args) {
        try {
            recordScore(-1);
        } catch (MyRuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void recordScore(int score) {
        if (score < 0 || score > 100) {
            throw new MyRuntimeException("MyRuntimeException 分数异常：" + score);
        }
    }
}

// 自定义的运行时时异常
class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String msg) {
        super(msg);
    }
}
