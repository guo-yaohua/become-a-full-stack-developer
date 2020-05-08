package com.gyh.test;

public class TestDemo {
    public static void main(String[] args) {
    }
}

class Student {

    private String name;
    private int age;
    private boolean isMale;

    public Student(String name, int age, boolean isMale) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")) {
            return;
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
