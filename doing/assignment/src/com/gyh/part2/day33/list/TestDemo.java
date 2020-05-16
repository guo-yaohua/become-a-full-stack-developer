package com.gyh.part2.day33.list;

public class TestDemo {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add("hello");
        list.add("world");
        list.listPrint();

        System.out.println(list.contains("world"));

        list.remove(0);
        list.listPrint();

        System.out.println(list.size());
    }
}
