package com.gyh.part2.day28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Solution2 {
    public static void main(String[] args) {
        Collection c = new ArrayList();
        c.add(new Student("张三", true, 1));
        c.add(new Student("李四", true, 2));
        c.add(new Student("王二", false, 3));

        for (Iterator it = c.iterator(); it.hasNext();) {
            Student student = (Student) it.next();
            System.out.println(student.toString());
            if ("张三".equals(student.name)) {
                it.remove();
            }
        }

        System.out.println("删除”张三“后：");
        for (Iterator it = c.iterator(); it.hasNext();) {
            Student student = (Student) it.next();
            System.out.println(student.toString());
        }
    }
}

class Student {
    String name;
    boolean isMale;
    long studentNum;

    public Student(String name, boolean isMale, long studentNum) {
        this.name = name;
        this.isMale = isMale;
        this.studentNum = studentNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", isMale=" + isMale +
                ", studentNum=" + studentNum +
                '}';
    }
}
