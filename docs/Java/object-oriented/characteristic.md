## 封装

### 封装的概念

封装是一种信息隐藏技术，是指将数据和基于数据的操作封装在一起。数据被保护在内部，系统的其他部分只有通过在数据外面的被授权的操作才能够进行交互。目的在于将类使用者和类设计者分开。  

在面向对象的编程中，用类来封装相关的数据和方法保证了数据的安全和系统的严密性。

### JavaBean

JavaBean 是特殊的 Java 类，使用 Java 语言书写，并且遵守 JavaBean API 规范。JavaBean 与其它 Java 类相比的特征：
- 提供一个默认的无参构造函数。

- 需要被序列化并且实现了 Serializable 接口。

- 可能有一系列可读写属性。

- 可能有一系列的 Getter 或 Setter 方法。

```java
public class ClassName implements java.io.Serializable{

    // 成员变量（考虑访问权限）

    // Getter 或 Setter 方法

    // 无参构造方法（必须）
    // 带参构造方法（建议）

    // 成员方法
}
```

以学生类为例编写符合 JavaBean 规范的类：
```java
public class StudentBean implements java.io.Serializable{

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentBean() {
    }

    public StudentBean(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

## 继承

### 继承的概念

继承是使用已存在的类的定义作为基础创建新类的技术，新类的定义可以增加新的数据或新的功能，也可以使用父类的功能，但不能选择性地基础父类。

被继承的类称之为父类（基类或超类），继承其他类的类称之为子类（派生类，导出类）。

子类继承父类，于是子类拥有了父类的成员，同时子类本身又可以定义属于自己的成员。因此子类对象中包含的数据由两部分组成：  
- 父类包含的可继承的成员（成员变量和成员方法）。

- 子类中自己定义的成员。

一旦子类继承了父类，那么在创建子类对象、初始化子类对象的过程及子类对象的初始化过程和之前相比也会有较大的不同。根本原因在于子类对象初始化时要初始化父类对象和子类对象两部分数据，而且父类对象（即父类成员部分）都应该先于子类对象（即子类中自己定义的成员部分）被初始化。  
这种优先关系可以从 2 个角度去理解：  
- 直觉：儿子能继承父亲的前提是父亲先存在。

- JVM：子类成员变量的初始化可能依赖于父类成员。

继承的语法：
```java
class 子类名 extends 父类名 {} 
```

!> 在 Java 中只能实现单重继承，即 extends 关键字后只能跟一个父类名。当然这并不意味着一个 Java 类只能继承某一个类的成员（可以间接继承）。

继承的优点：
- 代码复用。

- 提高了代码的可维护性。

- 弱化 Java 中的类型约束（多态的前提）。

继承的缺点：父类的修改可能会出现在所有子类中（我们无法选择这些修改可以反映在哪些子类中，不可以反映在哪些子类中）。

继承的注意事项：  
- 子类只能访问父类所有非私有的成员（成员方法和成员变量）。

- 子类不能继承父类的构造方法。

### 子类对象初始化

在 Java 语言中，子类对象的初始化有两种：隐式初始化和显式初始化。
- 隐式初始化：当父类提供了默认的构造方法且子类的构造方法中没有显式调用父类的其它构造方法时，在执行子类的构造方法之前会自动执行父类的构造方法。  

- 显式初始化：通过 super 关键字。  

示例：  
```java
class ExplicitFather {

    int fatherInt;
    
    public ExplicitFather(int fatherInt) {
        this.fatherInt = fatherInt;
    }
}

class ExplicitSon extends ExplicitFather{

    int sonInt;
    
    public ExplicitSon(int fatherInt, int sonInt) {
        super(fatherInt);
        this.sonInt = sonInt;
    }
}
```

**this** 代表当前这个对象，代表对象的内存空间标识（当前类定义的内容）。  
**super** 代表父类对象的内存空间的标识（父类定义的内容）。

super 用法：  
- 访问成员变量：`super.成员变量`。

- 访问构造方法：`super(实参列表)`。

- 访问成员方法：`super.成员方法()`。

注：
- 执行父类方法时，通过同名成员变量的变量名访问到的是父类中定义的成员变量。

- 如果在子类中定义了和父类同名的成员变量，则在子类类体中通过同名变量名访问到的是子类中定义的成员变量，看起来就好像父类成员变量在子类中被隐藏。可以通过 super 关键字在子类对象中访问到父类中的同名成员变量。

### 方法重载

方法重载（方法覆盖）是子类对父类允许继承的方法的实现过程进行重写，返回值和形参都不变。即外壳不变，核心重写。  

方法重载的使用场景：当子类继承父类之后，想要修改父类的方法，就可以使用方法重载。  

示例：   
```java
class Animal {
    public void move() {
        System.out.println("动物可以移动");
    }
}

class Dog extends Animal {

    @Override
    public void move() {
        System.out.println("狗可以跑和走");
    }
}

public class TestDemo {
    public static void main(String[] args) {

        Animal animal = new Animal();
        Dog dog = new Dog();

        animal.move();
        dog.move();
    }
}

/* 运行结果：
动物可以移动
狗可以跑和走
*/
```

发生方法重载时：
- 在子类方法体访问到的是子类中定义的方法。

- 在父类方法体中执行发生重载的方法，访问到的仍然是子类中定义的方法。   

示例：   
```java
class Animal {

    public void act() {
        this.move();  // 父类方法体中执行发生重载的方法
    }

    public void move() {
        System.out.println("动物可以移动");
    }
}

class Dog extends Animal {

    @Override
    public void move() {
        System.out.println("狗可以跑和走");
    }
}

public class TestDemo {
    public static void main(String[] args) {

        Dog dog = new Dog();
        dog.act();
    }
}

/* 运行结果：
狗可以跑和走
*/
```

方法重载主要看的是子类和父类的方法声明部分：访问权限、返回值和方法签名。
- 访问权限：并非子类和父类方法的访问权限要相同，只要子类方法的访问权限不小于父类方法的访问权限。

- 返回值：
  - 基本数据类型的方法返回值：子类必须和父类相同。

  - 引用数据类型：子类父类返回值类型相同或子类方法返回值类型是父类方法返回值类型的子类类型。

- 方法签名：子类方法的方法签名必须和父类一样。

注：  
- 父类中私有方法不能被重写。

- 静态方法不能被重写！

### final 关键字

final 是最终的意思，可以修饰类、变量和成员方法。  
- 修饰类，类不能被继承。

- 修饰变量，变量就变成了常量且只能被赋值一次。  
  - 修饰成员变量：必须在定义时初始化。  
    如果成员变量被 final 修饰，则必须在对象创建完毕之前给对象的该成员变量值赋值。

  - 修饰局部变量：可以在定义时初始化，也可以选择在构造方法中进行初始化。  
    如果局部变量被 final 修饰，那么该变量必须在使用之前赋值。

- 修饰方法，方法不能被重写。


## 多态  

### 多态的概念

多态：同一个对象的行为在不同的时刻或条件下表现出不同的效果。  

示例：
```java
class Animal {
    public void act() {
        System.out.println("动物行为");
    }
}

class Dog extends Animal {
    public void act() {
        System.out.println("狗可以看家");
    }
}

class Cat extends Animal {
    public void act() {
        System.out.println("猫可以抓老鼠");
    }
}

public class TestDemo {
    public static void main(String[] args) {

        Animal animal = new Dog();
        animal.act();

        animal = new Cat();
        animal.act();
    }
}

/* 运行结果：
狗可以看家
猫可以抓老鼠
*/
```

多态的前提条件：
- 继承。

- 方法重载。

- 父类引用指向子类对象。

父类引用指向子类对象去访问子类对象中的成员时（多态的特征）：
- 成员变量：编译看左边，运行看左边。  
  通过引用变量访问到的子类成员的范围，是由引用类型来决定的。

- 成员方法：编译看左边，运行看右边。  
  通过引用变量访问到的方法，是由引用实际指向的对象来决定的。

多态的好处：
- 提高了程序的维护性（由继承保证）。

- 提高了程序的扩展性（由多态保证）。

多态的弊端：不能访问子类特有功能（通过多态的转型可以解决）。

### 多态的转型

多态中的转型：  
- 向上转型：从子到父，即父类引用指向子类对象。  
  注意：向上转型，是 Java 语言天生就允许的。

- 向下转型：从父到子，即父类引用转为子类对象的引用。  
  格式：
  ```java
  子类类型 变量名 = (子类类型) 父类类型的变量;
  ```  
  注：向下转型是不安全的，因此 Java 语言默认不允许向下转型。但是通过 instanceof 关键字可以做到安全的向下转型。
  
instanceof 关键字：判断其左边对象是否为其右边类的实例，返回 boolean 类型的数据。可以用来判断继承中的子类的实例是否为父类的实现。  

语法：
```java
对象名 instanceof 类名
```
示例：
```java
Animal animal = new Dog();

if (animal instanceof Dog) {
    Dog dog = (Dog) animal;
}
```