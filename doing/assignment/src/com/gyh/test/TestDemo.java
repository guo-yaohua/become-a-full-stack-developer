package com.gyh.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TestDemo {
    // 思路 1
    public static List disctinct1(List list) {
        List result = new ArrayList();
        for(Iterator it = list.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (!result.contains(obj)) result.add(obj);
        }
        return result;
    }

    // 思路 2
    public static void disctinct2(List list) {
        for(ListIterator it = list.listIterator(list.size()); it.hasPrevious(); ) {
            Object element = it.previous();
            List subList = list.subList(0, it.nextIndex());
            if (subList.contains(element)) it.remove();
        }
    }

    public static void main(String[] args) {
        // [a, b, c, a, a, b, c]
        List list = new ArrayList();
        list.add('a');
        list.add('b');
        list.add('c');
        list.add('a');
        list.add('a');
        list.add('b');
        list.add('c');

        System.out.println(disctinct1(list));

        disctinct2(list);
        System.out.println(list);
    }
}


