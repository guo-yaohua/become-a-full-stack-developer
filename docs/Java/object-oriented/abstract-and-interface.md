## 抽象类

抽象类：在普通类的结构里面增加抽象方法的组成部分。  
抽象方法：没有方法体的方法。

抽象类和抽象方法必须用 abstract 关键字修饰：
```java
abstract class 类名 {
    public abstract void 方法名();
}
```  

抽象类不一定有抽象方法，有抽象方法的类一定是抽象类。

抽象类的子类可以是抽象类，也可以是具体类。

抽象类不能直接实例化，可以间接实例化：
```java
抽象类类型 变量名 = new 具体子类();
```

抽象类的成员方法可以是抽象方法，也可以是非抽象方法。一个不包含抽象方法的抽象类的意义：
- 虽然不包含抽象方法，但是依然无法直接使用它（new 该类型的对象）。  

- 如果别人使用你定义的抽象类，就必须自己定义一个子类继承抽象类。此时可能就会查看抽象类的定义，你在抽象类中的注释就会提示代码的使用者哪些方法适用哪些场景。

!> 代码中永远是通过多态调用子类覆盖抽象父类来使用 abstract 定义的抽象方法，而被 private，final，static 关键字修饰的方法都不能在子类中被覆盖，因此 private，final，static 这三个关键字不能和 abstract 共存。

## 接口

如果一个类只是由抽象方法和全局常量组成的，在这种情况下通常不会将其定义为一个抽象类，而是定义为接口。所以所谓的接口严格来讲就属于一个特殊的类，这个类里面只有抽象方法和全局常量。  

!> 接口不是类！而是对类的一组需求描述，这些类要遵从接口描述的统一格式进行定义。  

接口用关键字 interface 定义，格式：`interface 接口名 {}`。  

类和接口都可以用来表示数据类型（类和接口是地位对等的），只不过他们的侧重点不一样。
- 类定义的是一个数据集合基于这个数据集的一组操作（行为），类所描述的这一组行为，它们是有关系的（间接），都可以访问同一个数据集合。  

- 接口表示数据类型，侧重于描述一组具有特殊功能的行为。这些行为可以完全没有任何关系。

类和接口可以有实现关系（类可以实现接口），这种实现关系其实是一种实质上的继承关系。  

类实现接口用 implements 表示，格式：
```java
class 类名 implements 接口名 {}
```

接口的原则：  
- 接口不能直接实例化。

- 接口的子类可以是抽象类也可以是具体类。

接口的特点：  
- 无构造方法。

- 成员变量：只能是常量，修饰符 public static final。

- 成员方法：只能是抽象方法，修饰符 public abstract。

- 接口与接口之间可以实现多重继承，一个类也可以实现多个接口。一个考虑接口的比较完整的类定义语法：  
  ```java
  class 类名 extends 父类 implements 接口1, 接口2 ... {

  }
  ```

## 抽象类和接口比较

抽象类和接口的比较：
- 成员区别  
  抽象类：变量、抽象方法、非抽象方法；  
  接口：常量、抽象方法。  

- 关系区别  
  类与类：继承、单继承；  
  类与接口：实现、单实现、多实现；  
  接口与接口：继承、单继承、多继承。  

- 设计理念区别  
  抽象类：被继承体现的是共性功能。  
    - 抽象类可以被其他类继承，而且子类只能 extends 一个类。
    - 抽象被子类继承之后，子类和抽象类的关系是「is a」。  
  
  接口：被实现体现的是扩展功能。  
    - 一个类可以同时多个接口。
    - 类实现接口之后，类和接口的关系用「like a」来描述。

从 JDK8 开始，接口中可以定义两种特殊的方法，这两种方法可以有方法体，默认的访问权限都是 public。
- 默认方法：它就是一种折中，通过添加默认方法的方式修改接口，不会对已经实现接口的其他类造成影响。

- 静态方法：作为工具方法来使用。  
