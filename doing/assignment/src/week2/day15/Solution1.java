package week2.day15;

public class Solution1 {
    public static void main(String[] args) throws CloneNotSupportedException{
        ThirdLevel thirdLevel = new ThirdLevel(10, 20);
        SecondLevel secondLevel = new SecondLevel(1, 2.2, thirdLevel);
        FirstLevel firstLevel = new FirstLevel(30, 0.5, secondLevel);

        System.out.println("原对象：" + firstLevel);

        FirstLevel firstClone = (FirstLevel) firstLevel.clone();
        System.out.println("克隆对象：" + firstLevel);

        secondLevel.secondDoubleValue = 10000;
        thirdLevel.thirdIntValue = -100;
        System.out.println("修改后的原对象：" + firstLevel);
        System.out.println("修改后的克隆对象" + firstClone);
    }
}

class FirstLevel implements Cloneable {
    int firstIntValue;
    double firstDoubleValue;
    SecondLevel second;

    public FirstLevel(int firstIntValue, double firstDoubleValue, SecondLevel second) {
        this.firstIntValue = firstIntValue;
        this.firstDoubleValue = firstDoubleValue;
        this.second = second;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //利用 Object 的 Clone 方法，
        FirstLevel first = (FirstLevel) super.clone();
        //修改 first 对象中引用类型成员变量，比如 second 成员变量引用，指向被复制的那个 FirstLevel 对象的 second 对象的拷贝，
        return first;
    }

    public String toString() {
        return "FirstLevel{" + "firstIntValue=" + firstIntValue +
                ", firstDoubleValue=" + firstDoubleValue +
                ", second=" + second + "}";
    }
}

class SecondLevel implements Cloneable{
    int secondIntValue;
    double secondDoubleValue;
    ThirdLevel third;

    public SecondLevel(int secondIntValue, double secondDoubleValue, ThirdLevel third) {
        this.secondIntValue = secondIntValue;
        this.secondDoubleValue = secondDoubleValue;
        this.third = third;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        SecondLevel clone = (SecondLevel) super.clone();
        // 修改 SecondLevel 这个对象中，引用类型的成员变量的值，让他指向，复制之后的成员变量所指向对象地址
        return clone;
    }

    public String toString() {
        return "SecondLevel{" + "secondIntValue=" + secondIntValue +
                ", secondDoubleValue=" + secondDoubleValue +
                ", third=" + third + "}";
    }
}

class ThirdLevel implements Cloneable{
    int thirdIntValue;
    double thirdDouleValue;

    public ThirdLevel(int thirdIntValue, double thirdDouleValue) {
        this.thirdIntValue = thirdIntValue;
        this.thirdDouleValue = thirdDouleValue;
    }

    public String toString() {
        return "ThirdLevel" + "thirdIntValue=" + thirdIntValue +
                ", thirdDouleValue=" + thirdDouleValue + "}";
    }
}
