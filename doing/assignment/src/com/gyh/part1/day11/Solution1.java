package com.gyh.part1.day11;

public class Solution1 {
    public static void main(String[] args) {
        Student student = new Student("张三", true, 18, 112);
        Teacher teacher = new Teacher("李四", false, 25, "Java");
        Soldier solider = new Soldier("王二", true, 22, 110);

        System.out.println("学生信息：");
        student.print();
        System.out.println("教师信息：");
        teacher.print();
        System.out.println("军人行为：");
        solider.action();

    }
}

class Person {
    // 成员变量
    String name;
    boolean gender;
    int age;

    // 构造方法
    public Person(String name, boolean gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // 成员方法
    public void print() {
        System.out.println(this.name + " -- " + (gender ? "男" : "女") + " -- " + age);
    }
}

class Student extends Person {
    int id;

    public Student(String name, boolean gender, int age, int id) {
        super(name, gender, age);
        this.id = id;
    }

    public void print() {
        System.out.println(this.name + " -- " + (gender ? "男" : "女") + " -- " + age + " -- " + id);
    }
}

class Teacher extends Person {
    String course;

    public Teacher(String name, boolean gender, int age, String course) {
        super(name, gender, age);
        this.course = course;
    }

    public void print() {
        System.out.println(this.name + " -- " + (gender ? "男" : "女") + " -- " + age + " -- " + course);
    }
}

class Soldier extends Person {
    int id;

    public Soldier(String name, boolean gender, int age, int id) {
        super(name, gender, age);
        this.id = id;
    }

    void action() {
        System.out.println("训练！");
    }
}