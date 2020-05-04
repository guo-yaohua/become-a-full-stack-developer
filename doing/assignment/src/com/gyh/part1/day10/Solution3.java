package com.gyh.part1.day10;

public class Solution3 {
    public static void main(String[] args) {
        int n = 1;

        do {
            if (Student.getStudent() == null) {
                System.out.println("第 " + n + " 个 Student 类创建失败");
                break;
            }
            System.out.println("第 " + n + " 个 Student 类创建成功");
            n++;
        } while(true);
    }
}

class Student {

    // 成员变量
    String name;

    // 构造方法
    private Student(){
    }

    // 成员方法
    private static int i = 1;
    public static Student getStudent(){
        if (i <= 10) {
            i++;
            return (new Student());
        }
        return null;
    }
}
