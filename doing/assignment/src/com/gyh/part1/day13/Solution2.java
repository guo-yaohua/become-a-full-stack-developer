package com.gyh.part1.day13;

public class Solution2 {
    public static void main(String[] args) {
        TableTennisAthlete tableTennisAthlete = new TableTennisAthlete("张三", 18, 2000);
        tableTennisAthlete.eat();
        tableTennisAthlete.train();
        tableTennisAthlete.learnEnglish();
    }
}

// 人类
abstract class Human {
    String name;
    int age;
    int salary;

    Human(String name, int age, int salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public void eat() {
        System.out.println(this.name + "在吃饭！");
    }
    public void sleep() {
        System.out.println(this.name + "在睡觉！");
    }
}

// 教练员
abstract class Coach extends Human {
    int bonus;

    Coach(String name, int age, int salary, int bonus) {
        super(name, age, salary);
        this.bonus = bonus;
    }

    public abstract void teach();
}

// 运动员

abstract class Athlete extends Human {
    Athlete(String name, int age, int salary) {
        super(name, age, salary);
    }

    public abstract void train();
    public abstract void match();
}

// 接口：学习英语
interface SpecialSkill {
    public void learnEnglish();
}

// 乒乓球运动员
class TableTennisAthlete extends Athlete implements SpecialSkill {
    TableTennisAthlete(String name, int age, int salary) {
        super(name, age, salary);
    }

    @Override
    public void train() {
        System.out.println(this.name + "在乒乓球训练");
    }

    @Override
    public void match() {
        System.out.println(this.name + "在乒乓球比赛");
    }

    @Override
    public void learnEnglish() {
        System.out.println(this.name + "在学习英语");
    }
}

// 乒乓球教练
class TableTennisCoach extends Coach implements SpecialSkill {

    TableTennisCoach(String name, int age, int salary, int bonus) {
        super(name, age, salary, bonus);
    }

    @Override
    public void teach() {
        System.out.println(this.name + "在乒乓球教学");
    }

    @Override
    public void learnEnglish() {
        System.out.println(this.name + "在学习英语");
    }
}

// 篮球运动员
class BasketballAthlete extends Athlete {

    BasketballAthlete(String name, int age, int salary) {
        super(name, age, salary);
    }

    @Override
    public void train() {
        System.out.println(this.name + "在蓝球训练");
    }

    @Override
    public void match() {
        System.out.println(this.name + "在蓝球比赛");
    }
}

class BasketballCoach extends Coach {

    BasketballCoach(String name, int age, int salary, int bonus) {
        super(name, age, salary, bonus);
    }

    @Override
    public void teach() {
        System.out.println(this.name + "在篮球教学");
    }
}