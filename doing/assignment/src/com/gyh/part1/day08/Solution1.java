package com.gyh.part1.day08;

public class Solution1 {
    public static void main(String[] args) {
        Student stu1 = new Student("张三", 18, true, 1);
        Student stu2 = new Student("李四", 25, false, 2);

        stu1.print();
        stu2.print();
    }
}

class Student {
    String name;
    int age;
    boolean isMale;
    int sno;

    // 构造方法
    public Student() {
        this("未知", -1, true, -1);
    }
    public Student(String name) {
        this(name, -1, true, -1);
    }
    public Student(String name, int sno) {
        this(name, -1, true, sno);
    }
    public Student(String name, int age, boolean isMale) {
        this(name, age, isMale, -1);
    }
    public Student(String name, int age, boolean isMale, int sno) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.sno = sno;
    }

    // 成员方法
    public void print() {
        System.out.println(name + "---" + age + "---" + (isMale ? "男" : "女") + "---" + sno);
    }
}
