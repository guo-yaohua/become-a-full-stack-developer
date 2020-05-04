package com.gyh.part1.day12;

public class Solution2 {
    public static void main(String[] args) {

    }
}

class TestFinal {
    final int finalField1 = 1;
    final int finalField2;
    final int finalField3;

    {
        finalField2 = 2;
    }

    public TestFinal(int a) {
        finalField3 = a;
    }
}
