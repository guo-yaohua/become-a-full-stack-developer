package com.gyh.part1.day10;

import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("输入正方形边长：");
        int n = sc.nextInt();
        Square sq = new Square(n);
        System.out.println("正方形面积：" + sq.getArea());
        System.out.println("正方形周长：" + sq.getPerimeter());

        System.out.print("\n修改正方形边长：");
        n = sc.nextInt();
        sq.setLength(n);
        System.out.println("此时正方形面积：" + sq.getArea());
        System.out.println("正方形周长：" + sq.getPerimeter());


        // 长方形
        System.out.print("\n输入长方形的长、宽：");
        int l = sc.nextInt();
        int w = sc.nextInt();
        Rectangle re = new Rectangle(l, w);
        System.out.println("长方形面积：" + re.getArea());
        System.out.println("长方形周长：" + re.getPerimeter());

        System.out.print("\n修改长方形的长、宽：");
        l = sc.nextInt();
        w = sc.nextInt();
        re.setLength(l);
        re.setWidth(w);
        System.out.println("此时长方形面积：" + re.getArea());
        System.out.println("长方形周长：" + re.getPerimeter());

        sc.close();
    }
}

class Square {
    // 成员变量
    private int length;

    // 构造方法
    public Square(int length) {
        this.length = length;
    }

    // 成员方法
    public int getArea() {
        return this.length * this.length;
    }
    public int getPerimeter() {
        return this.length * 4;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

class Rectangle {
    // 成员变量
    private int length;
    private int width;

    // 构造方法
    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }

    // 成员方法
    public int getArea() {
        return this.length * this.width;
    }
    public int getPerimeter() {
        return (this.length + this.width) * 2;
    }

    public void setLength(int length) {
        this.length = length;
    }
    public void setWidth(int width) {
        this.width = width;
    }
}