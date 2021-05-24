# 三、高级特性

## 目录

- [三、高级特性](#三高级特性)
  - [目录](#目录)
  - [7 注解](#7-注解)
    - [7.1 注解格式](#71-注解格式)
    - [7.2 注解的使用](#72-注解的使用)
    - [7.3 注解处理器](#73-注解处理器)
    - [7.4 元注解](#74-元注解)
      - [7.4.1 @Rentention](#741-rentention)
      - [7.4.2 @Target](#742-target)
    - [7.5 注解的使用场景](#75-注解的使用场景)
  - [8 Java 内存管理](#8-java-内存管理)
    - [8.1 Memoery](#81-memoery)
    - [8.2 GC](#82-gc)
      - [8.2.1 如何确定垃圾](#821-如何确定垃圾)
      - [8.2.2 如何回收垃圾](#822-如何回收垃圾)
    - [8.3 GC 相关概念](#83-gc-相关概念)
    - [8.4 内存相关问题](#84-内存相关问题)





## 7 注解

Java 语言中本身只定义了极少数的注解，当然不能满足我们所有的需求，但是 Java 语言可以让我们自定义注解，满足自己的需求。  

### 7.1 注解格式

自定义注解格式：
```java
public @interface 注解名 {
  定义体
}
```

一个注解数据类型（描述一种类型额外信息），定义了：
- 具体包含多少条，具体的信息。

- 每种信息，是怎样的。  
  注：注解体中定义的，每一条具体的信息，都有标准形式

注：
- 注解的定义和类定义以及接口定义非常像（尤其是接口）。

- 像类、接口在 Java 语言中的地位一样， 它也代表数据类型，定义的一个注解就是一种数据类型。

- `@` 必不可少，少了 `@` 就成了接口定义。

- 注解之间不能继承。

自定义注解示例：  
```java
// 该注解用来表示学生年龄的上下限
public @interface AgeContraint {
  int maxAge();
  int minAge();
}
```

自定义注解体的说明：
- 注解体的格式类似于接口中的方法定义，但含义完全不同。

- 方法名就是数据的名称，方法的返回值类型表示数据值的类型。  
  比如，截图中的两个方法定义表示该注解包含两个数据，这两个数据的返回值都为 int 类型，就说明年龄上下限的取值类型是 int 类型。

- 注解信息的取值的类型只能是以下几种:
  - 所有的基本数据类型。
  - String 类型。
  - Class 类型。
  - 注解类型。
  - 以及以上类型的数组。

### 7.2 注解的使用

定义某种类型的注解，实际上也就是定义了某种类型的额外信息的「标准形式」。

注解的使用：在定义好注解类型之后，利用注解实例，给代码添加额外信息。  
格式：  
```java
@注解的类型名(属性名1 = 属性值1， 属性名2 = 属性值2，....)
```
- 属性名：注解定义中，每一条具体的信息的名字。

- 属性值：赋予的实际值。

注：在使用注解实例的时候，一定要保证注解定义中的每条数据（每一个属性）必须有确定值。
- 在使用注解实例的时候，给每一个属性显示赋值。

- 可以在定义注解的时候，给注解中定义的某个属性，声明默认值。

- 对于合法的引用类型, 比如 String 类型, 它的默认取值不能是 null。

- 在有一种特殊情况下，对于注解实例中的属性赋值可以稍作简化，条件是：  
  1. 属性名称固定 value；  
  2. 当在注解实例中，仅仅只需要给 value 属性赋值的时候。  
  
  此时，给 value 属性赋值的时候，不用写属性名。  
  示例：  
  ```java
  // 接口定义形式
  public @interface ActionListenerFor {
    String value();
  }

  // 标准的书写形式
  @ActionListenerFor(value = "yellowButton")

  // 简化的书写形式
  @ActionListenerFor("yellowButton")
  ```

### 7.3 注解处理器

注解的本质只是表达代码之外的额外信息，至于这些信息究竟用来干什么，与注解本身是没有关系的。至于利用这些额外信息完成一些特殊的功能，则是由注解处理器来完成的。  

注解处理器本身，没有什么特殊的语法，只是通过一些其他方式（比如反射技术），获得所需注解信息，然后根据需求实现特殊功能。  

示例：  
```java
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 创建注解处理器对象
        StudentFactory studentFactory = new StudentFactory();

        int age = 35;
        String name = "张三";
        Student student = studentFactory.getStudent(age, name);
        System.out.println(student);
    }
}

class Student {

    @AgeConstraint(minAge = 18, maxAge = 25)
    int age;

    @NameLengthConstraint(lengthLimit = 5)
    String name;

    private Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}


@interface AgeConstraint {
    int maxAge();
    int minAge();
}

@interface NameLengthConstraint {
    int  lengthLimit();
}

/*  StudentFactory 就是用来接收 name 和 age 的额外约束信息，并处理额外信息的注解处理器。实现如下效果：
 *   1. 只有初始化参数，name 和 age 满足约束条件的时候，才能成功创建 Student 对象
 *   2. 否则，拒绝创建，并抛出异常（根据约束信息，要实现的特殊效果）
 */
class StudentFactory {

    // 目标 Class 对象，因为注解实例放在类定义中使用
    private Class studentClz;

    public StudentFactory() {
        this.studentClz = Student.class;  // 操作对象为 Student，因此直接获取
    }

    // 其他人可以通过调用该方法，获取新创建的 Student 对象
    // 同时，该方法接收，待创建的 Student 对象的两个初始化参数
    public Student getStudent(int age, String name)
            throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 判断参数值，如果不满足约束，直接抛出异常
        judgeAge(age);

        judgeNameLength(name);

        // 通过反射，创建 Student 对象，并且用合法的参数值，初始化该对象，并返回
        Constructor con = studentClz.getDeclaredConstructor(int.class, String.class);
        con.setAccessible(true);
        Student st = (Student) con.newInstance(age, name);

        return st;
    }

    /**
     * 用来判断 age 参数是否满足约束
     * @param age 待判断的参数
     */
    private void judgeAge(int age) throws NoSuchFieldException {

        // 表示 age 成员变量约束条件的注解对象，属于 age 成员变量信息
        Field ageField = studentClz.getDeclaredField("age");

        //提前解决权限问题
        ageField.setAccessible(true);

        // isAnnotationPresent 判断在成员变量中，是否包含目标类型的注解信息
        if (ageField.isAnnotationPresent(AgeConstraint.class)) {
            // 获取指定类型的注解实例
            AgeConstraint ageAnnotation = ageField.getAnnotation(AgeConstraint.class);

            // 从形式上来看，要获取哪个数值，就在注解实例上调用那个属性对应的方法
            int maxAge = ageAnnotation.maxAge();
            int minAge = ageAnnotation.minAge();

            // 判断参数是否满足条件
            if (age < minAge || age > maxAge) {
                // 如果参数不满足约束条件就抛出异常
                throw new IllegalArgumentException("age 参数非法：age = " + age);
            }
        }
    }

    /**
     * 判断 name 参数，长度是否满足约束条件
     * @param name 带判断的参数值
     */
    private void judgeNameLength(String name) throws NoSuchFieldException {

        Field nameField = studentClz.getDeclaredField("name");

        if (nameField.isAnnotationPresent(NameLengthConstraint.class)) {
            NameLengthConstraint nameAnnotation = nameField.getAnnotation(NameLengthConstraint.class);

            int lengthLimit = nameAnnotation.lengthLimit();

            if (name.length() > lengthLimit) {
                throw new IllegalArgumentException("name 参数非法: name = " + name);
            }
        }
    }
}

/* 允行结果：
Student{age=35, name='张三'}
*/
```

### 7.4 元注解

运行程序后，发现即使定义了注解处理器，还是没实现我们的特殊效果，这和注解的保存级别有关。

和 Java 程序的一致，注解有 3 种保留级别：
- SOURCE：注解将被编译器丢弃。

- CLASS：注解在 class 文件中可用，但会被 JVM 丢弃（内存没有）。

- RUNTIME：JVM 在运行时也会保留注解信息。

注解默认情况下的保留级别是 CLASS （运行时已经没了）。

元注解：注解的注解。  

Java 中提供了 4 种元注解，我们最常用的是其中 2 个。

#### 7.4.1 @Rentention

@Rentention 元注解，来定义我们自己定义的注解的保留级别。
- RetentionPolicy.SOURCE。

- RetentionPolicy.CLASS。

- RetentionPolicy.RUNTIME。

示例：  
```java
@Retention(RetentionPolicy.RUNTIME)
@interface AgeConstraint {
    int maxAge();
    int minAge();
}

@Retention(RetentionPolicy.RUNTIME)
@interface NameLengthConstraint {
    int  lengthLimit();
}
```

将原本的自定义保留级别加入上节的代码，可得到异常：
```
Exception in thread "main" java.lang.IllegalArgumentException: age参数非法：age = 35
```
即表明注解处理器成功执行。

#### 7.4.2 @Target

@Target 元注解，注解可以作用的目标。
  - 给整个类添加注解。

  - 给类中成员变量添加注解。

  - 给类中的构造方法添加注解。

  - 给类中的普通方法添加注解。
  
示例：  
```java
// Target 可以有多个取值
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
@interface MyAnnotation {
  String name();

  OtherAnnotation test();
}
```

### 7.5 注解的使用场景

注解 VS 配置文件：
- 配置文件：  
  优点：可配置，不用改源码。  
  缺点：不直观，开发效率低。

- 注解：  
  优点：直观开发效率高。  
  缺点：硬编码，修改之后需要重新编译运行。

## 8 Java 内存管理

### 8.1 Memoery

Java 虚拟机在 Java 程序运行过程中会把它管理的内存划分为若干个不同的数据区域。这些区域有各自的用途以及创建和销毁的时间，有的区域随虚拟机的启动而存在，有些区域依赖用户线程的启动和结束而建立和销毁。

根据 《Java虚拟机规范》，Java 虚拟机所管理的内存将会包括如下图的几个运行时数据区：
<div align="center">
<img src="./img/p7.png">
</div>

- 程序计数器：程序计数器（Program Counter Register）是一块较小的内存空间，它的作用可以看做是当前线程所执行的字节码的行号指示器。

- Java 虚拟机栈：它描述的是 Java 方法执行的内存模型。每个方法被执行的时候都会同时创建一个栈帧（Stack Frame ）用于存储局部变量表、操作栈、动态链接、方法出口等信息。

- 本地方法栈：本地方法栈（Native Method Stacks）与虚拟机栈所发挥的作用是非常相似的，其区别不过是虚拟机栈为虚拟机执行 Java 方法（也就是字节码）服务，而本地方法栈则是为虚拟机使用到的 Native 方法服务。

- Java 堆：此内存区域的唯一目的就是存放对象实例。一个 JVM 实例只存在一个堆，堆内存的大小是可以调节的，堆内存是线程共享的。

- 方法区：方法区（Method Area）与 Java 堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量等数据。

### 8.2 GC

内存管理管理的方式通常有两种：
- 显式的内存管理（C/C++）：内存管理（内存的申请和释放）是程序开发者的职责。
  
  常见问题：  
  - 内存泄漏：内存空间已经申请，使用完毕后未主动释放。  

  - 野指针：使用了一个指针，但是该指针指向的内存空间已经被 free。

- 隐式的内存管理（Java/C#）：内存的管理是由垃圾回收器自动管理的。

  优点：增加了程序的可靠性，减小了 memory leak。  
  缺点：无法控制 GC 的时间，耗费系统性能（stop the world），无法消除内存泄漏。

GC（Garbage Collection），在 JVM 中，GC 的功能是由垃圾回收器来完成。

研究 GC，就必须要面对 3 个问题：
1. 如何确定垃圾；

2. 如何回收垃圾；

3. 何时触发 GC。

#### 8.2.1 如何确定垃圾

最简单的算法就是引用计数法：
1. 给对象添加一个引用计数器；

2. 每当一个地方引用它时，计数器加 1；

3. 每当引用失效时，计数器减少 1；

4. 当计数器的数值为 0 时，也就是对象无法被引用时，表明对象不可在使用。

但是这个算法存在一个致命的缺陷，无法解决循环引用的问题。为此，引入了另外一种跟搜索算法。

根搜索算的基本思想：
1. 将一系列称为「GC Roots」的对象作为起始点；

2. 从这些节点开始向下搜索；

3. 搜索所走的路径称为引用链；

4. 当一个对象到所有的 GC root 之间没有任何引用链相连时，就认为该对象变成了垃圾。

GC Roots 包含：
- 虚拟机栈中引用的对象。

- 方法区中的静态属性引用的对象。


#### 8.2.2 如何回收垃圾 

GC 中如何回收垃圾呢：
- 标记清除算法（Mark Sweep）：  
  <div>
  <img src="./img/p8.png">
  </div>

- 标记复制算法（Copy）：  
  <div align="center">
  <img src="./img/p9.png">
  </div>

- 标记整理算法（Mark Compact）：  
  <div align="center">
  <img src="./img/p10.png">
  </div>

- 分代收集算法（Generational Collection 商用）：这种算法并没有什么新的思想，只是根据对象存活周期的不同将内存划分为几块。一般是把 Java 堆分为新生代和老年代，这样就可以根据各个年代的特点采用最合适的收集算法。
  - 在新生代中，每次垃圾收集时都发现有大批对象死去，只有少量存活，那就选用复制算法，只需要付出少量存活对象的复制成本就可以完成收集。

  - 而老年代中因为对象存活率高、没有额外空间对它进行分配担保，就必须使用标记整理算法进行回收。

### 8.3 GC 相关概念

Shallow size 就是对象本身占用的内存大小，也就是对象头加成员变量占用内存大小的总和。

Retained size 是该对象自己的 shallow size 加上仅可以从该对象访问（直接或者间接访问）的对象的 shallow size 之和。

Retained size 是该对象被 GC 之后所能回收的内存的总和。

GC 触发的时机：
- 申请 heap space 失败后会触发 GC 回收。

- 系统进入 idle 后一段时间会进行回收。

- 主动调用 GC 进行回收。

### 8.4 内存相关问题

Out of Memory case：
- Heap  OOM 堆溢出。

- Stack Overflow 栈溢出。

内存泄漏（Memory Leak）是指程序中己动态分配的堆内存由于某种原因程序未释放或无法释放，造成系统内存的浪费，导致程序运行速度减慢甚至系统崩溃等严重后果。

注：内存泄露可能导致内存溢出，但不是必然导致内存溢出。
