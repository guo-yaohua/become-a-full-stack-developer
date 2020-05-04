package com.gyh.part1.day14;

public class Solution1 {
    public static void main(String[] args) {

    }
}


// 接口
interface Compute {
    double compute(double a, double b);
}

class ShowCompute {
    double a;
    double b;

    double add = new Compute() {
        public double compute(double a, double b) {
            return a + b;
        }
    }.compute(a, b);

    double sub = new Compute() {
        public double compute(double a, double b) {
            return a - b;
        }
    }.compute(a, b);

    double mul = new Compute() {
        public double compute(double a, double b) {
            return a * b;
        }
    }.compute(a, b);

    double div = new Compute() {
        public double compute(double a, double b) {
            return a / b;
        }
    }.compute(a, b);
}
