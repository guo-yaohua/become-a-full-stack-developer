# Day 15 Objectapi

## 作业
1. 完成对 FirstLevel 对象的深度克隆。  
```
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

    public void testProtected() throws CloneNotSupportedException {
    // 跨包子类访问父类 ，访问 FirstLevel 自己从 Object 继承下来的 Clone 方法
        clone(); //alt + enter

        FirstLevel firstLevel = new FirstLevel(10, 20);
        firstLevel.clone();
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
}

class ThirdLevel implements Cloneable{
    int thirdIntValue;
    double thirdDouleValue;

    public ThirdLevel(int thirdIntValue, double thirdDouleValue) {
        this.thirdIntValue = thirdIntValue;
        this.thirdDouleValue = thirdDouleValue;
    }
}
```