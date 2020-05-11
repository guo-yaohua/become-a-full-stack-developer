package com.gyh.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class TestDemo {
    public static void main(String[] args) {
        Collection c = new ArrayList();

        c.add("Hello");
        c.add("World");

        Iterator it = c.iterator();
        while (it.hasNext()) {
            String s = (String) it.next();
            if ("World".equals(s)) {
                c.remove(s);
            }
        }
    }
}

