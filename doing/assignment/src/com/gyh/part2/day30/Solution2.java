package com.gyh.part2.day30;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.ListIterator;

public class Solution2 {
    public static ArrayList removeRepetition(ArrayList list) {
        Collection visited = new HashSet();

        for (ListIterator listIterator = list.listIterator(); listIterator.hasNext(); ) {
            String s = (String) listIterator.next();
            if (visited.contains(s)) {  // 如果已经存在，就移除
                listIterator.remove();
            } else {
                visited.add(s);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("qwer");
        list.add("qwer");
        list.add("asdf");
        list.add("asdf");
        list.add("zxcv");
        System.out.println("方法运行前：" + list);
        list = removeRepetition(list);
        System.out.println("方法运行后：" + list);
    }
}
