package com.gyh.part1.day13;

public class Solution1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Car(2, 2, 2);
        System.out.println("Car 的速度 " + vehicle.speed());

        vehicle = new Plane(2, 2, 2);
        System.out.println("Plane 的速度 " + vehicle.speed());

        vehicle = new Ship(2, 2, 2);
        System.out.println("Ship 的速度 " + vehicle.speed());
    }
}

abstract class Vehicle {
    // 成员变量
    int a;
    int b;
    int c;

    // 构造方法
    Vehicle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    // 成员方法
    public abstract int speed();
}

class Car extends Vehicle {
    Car(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public int speed() {
        return this.a * this.b / this.c;
    }
}

class Plane extends Vehicle {
    Plane(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public int speed() {
        return this.a + this.b + this.c;
    }
}

class Ship extends Vehicle {
    Ship(int a, int b, int c) {
        super(a, b, c);
    }

    @Override
    public int speed() {
        return this.a + this.b / this.c;
    }
}
