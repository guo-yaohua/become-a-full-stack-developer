## 内部类

在 Java 语言中，类可以嵌套定义。

内部类：定义在其他类内部的类就称为内部类。约定把包含内部类的类，称之为外部类。  

示例：  
```java
class MemberOuter {

    private void outerPrivateMethod() {
        System.out.println("外部方法");
    }

    class MemberInner {
        public void innerMemberMethod() {
            System.out.println("内部方法");
        }

        public void accessOuter() {
            outerPrivateMethod();
        }
    }
    
}

public class TestDemo {

    public static void main(String[] args) {

        /*
        MemberOuter memberOuter = new MemberOuter();
        MemberOuter.MemberInner innerObj = memberOuter.new MemberInner();
        */
        MemberOuter.MemberInner innerObj = new MemberOuter().new MemberInner();
        innerObj.accessOuter();
        innerObj.innerMemberMethod();
    }

}

/* 运行结果：
外部方法
内部方法
*/
```

内部类的访问特点：  
- 内部类可以直接访问外部类的成员，包括私有。

- 外部类要访问内部类的成员，必须创建对象。



## 成员内部类和局部内部类

按照内部类在类中定义的位置不同，可以分为如下两种格式：  
- 成员位置（成员内部类）。

- 局部位置（局部内部类）。

成员内部类的定义位置：外部类的成员位置。  
如果将内部类看做一个整体，对于外部类而言成员内部类就类似于一个成员变量或成员方法的一个普通成员。所以成员内部类在静态上下文中也无法访问非静态的成员。

成员内部类对象的实例化语法：
```java
外部类.内部类 变量名 = new 外部类().new 内部类()
```

成员内部的常见修饰符：  
- private：保证成员位置内部类只对其外部类可见。

- static：一旦被 static 修饰，那么整个成员内部类就有了静态的访问特征。普通成员内部类依赖于外部类对象而存在，静态成员内部类作为外部类的一个静态成员，不再依赖于外部类对象而存在。创建静态成员内部类的语法：
  ```java
  外部类.内部类 变量名 = new 外部类.内部类()
  ```


局部内部类的定义位置：方法体内。

局部内部类的特征：可以创建内部类对象，通过对象调用内部类方法来使用局部内部类功能。所以局部内部类可以访问方法体中局部变量。但是被局部内部类对象访问的局部变量必须被 final 关键字修饰。这是因为局部内部类对象与局部变量的生命周期冲突：  
- 局部变量的生命周期，随着方法的执行结束，即栈帧销毁，其从内存中消失。

- 局部内部类对象存储在堆上，对象的销毁和方法栈帧没有直接关系。  

- 简单来说就是方法运行结束后，局部变量不存在了，但是对象还在。因此要想继续正常使用局部内部类对象，就要将被其访问的局部变量用 final 关键字修饰。

## 匿名内部类

不管是成员或局部位置内部类，要使用内部类都分成了两步：  
1. 定义内部类；

2. 创建内部类对象。

通过定义匿名内部类对象，可以将上面的两步变为一步。

匿名内部类语法：

```java
new 类名或者接口名() {
  方法覆盖
}
```

示例：
```java
// 定义一个接口
interface Print {  
    public void print();
}

public class TestDemo {

    public static void main(String[] args) {
        new Print() {
            @Override
            public void print() {
                System.out.println("HelloWorld");
            }
        }.print();
    }

}

/* 运行结果：
HelloWorld
*/
```

本质是一个继承了类或者实现了接口的子类匿名对象。

特征：匿名内部类对象只能在被创建的时候被访问一次。