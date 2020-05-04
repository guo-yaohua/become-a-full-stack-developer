package com.gyh.part1.day12;

public class Solution3 {
    public static void main(String[] args) {
        Persion persion = new Persion();
        persion.eat();

        persion = new SouthPerson();
        persion.eat();

        persion = new NorthPerson();
        persion.eat();
    }
}

class Persion {
    public void eat() {
        System.out.println("人要吃饭");
    }
}

class SouthPerson extends Persion {
    public void eat() {
        System.out.println("南方人吃炒菜和米饭");
    }
}

class NorthPerson extends Persion {
    public void eat() {
        System.out.println("北方人吃烩菜和馒头");
    }
}
