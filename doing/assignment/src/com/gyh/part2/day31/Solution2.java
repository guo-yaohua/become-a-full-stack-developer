package com.gyh.part2.day31;

import java.util.LinkedList;

public class Solution2 {
    public static void main(String[] args) {
        LinkedList<Object> list = new LinkedList<Object>();
        String s = "Hello";
        lru(list, 1, 5);
        lru(list, 2, 5);
        lru(list, s, 5);
        lru(list, "World", 5);
        lru(list, "Java", 5);

        System.out.println(list);

        lru(list, s, 5);
        System.out.println(list);
    }

    public static void lru (LinkedList<Object> list, Object object, int maxLen) {
        if (list.contains(object)) {
            list.removeLastOccurrence(object);
        }

        list.offerFirst(object);

        while (list.size() > maxLen) {
            list.removeLast();
        }
    }
}
