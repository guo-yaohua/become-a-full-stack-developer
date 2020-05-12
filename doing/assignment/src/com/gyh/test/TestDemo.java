package com.gyh.test;

import java.util.ArrayList;
import java.util.List;

public class TestDemo {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("hello");
        list.add("world");
        list.add("java");

        List subList = list.subList(1, 2);
        System.out.println(subList);

        subList.set(0, "WORLD");
        System.out.println(subList);
        System.out.println(list);
    }
}

