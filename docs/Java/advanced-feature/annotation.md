## 自定义注解

Java 语言中本身只定义了极少数的注解，但支持自定义注解满足自己的需求。  

自定义注解格式：  

```java
public @interface 注解名 {
  定义体
}
```

一个注解数据类型（描述一种类型额外信息），定义了：
- 具体包含多少条，具体的信息。

- 每种信息，是怎样的。  
  注：注解体中定义的每一条具体的信息都有标准形式。

!> 注解的定义和类定义以及接口定义非常像（尤其是接口）。  
像类、接口在 Java 语言中的地位一样， 它也代表数据类型。定义的一个注解就是一种数据类型。  
`@` 必不可少，少了 `@` 就成了接口定义。  
注解之间不能继承。

示例：  
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
  比如示例中的两个方法定义表示该注解包含两个数据，这两个数据的返回值都为 int 类型，就说明年龄上下限的取值类型是 int 类型。

- 注解信息的取值的类型只能是以下几种:
  - 所有的基本数据类型。
  - String 类型。
  - Class 类型。
  - 注解类型。
  - 以及以上类型的数组。


定义某种类型的注解，实际上也就是定义了某种类型的额外信息的「标准形式」。在定义好注解类型之后，利用注解实例，给代码添加额外信息。  
格式：

```java
@注解的类型名(属性名1 = 属性值1， 属性名2 = 属性值2，....)
```

!> 在使用注解实例的时候，一定要保证注解定义中的每条数据（每一个属性）必须有确定值。

在使用注解实例的时候，给每一个属性显示赋值。可以在定义注解的时候，给注解中定义的某个属性，声明默认值。对于合法的引用类型, 比如 String 类型, 它的默认取值不能是 null。在有一种特殊情况下，对于注解实例中的属性赋值可以稍作简化，条件是：  
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

## 注解处理器

注解的本质只是表达代码之外的额外信息，至于这些信息究竟用来干什么，与注解本身是没有关系的。至于利用这些额外信息完成一些特殊的功能，则是由注解处理器来完成的。  

注解处理器本身没有什么特殊的语法，只是通过一些其他方式（比如反射技术），获得所需注解信息，然后根据需求实现特殊功能。  

示例：  

<!-- tabs:start -->

#### **TestDemo.java**

```java
import java.lang.reflect.InvocationTargetException;

public class TestDemo {
    public static void main(String[] args) throws NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {

        // 创建注解处理器对象
        StudentFactory studentFactory = new StudentFactory();

        int age = 40;
        String name = "张三";
        Student student = studentFactory.getStudent(age, name);
        System.out.println(student);
    }
}

/* 运行结果：
Exception in thread "main" java.lang.IllegalArgumentException: age 参数非法：age = 40
*/
```

#### **Student.java**

```java
public class Student {

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
```

#### **AgeConstraint.java**

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AgeConstraint {
    int maxAge();
    int minAge();
}
```

#### **NameLengthConstraint.java**

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NameLengthConstraint {
    int  lengthLimit();
}
```

#### **StudentFactory.java**

```java
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 *   StudentFactory 就是用来接收 name 和 age 的额外约束信息，并处理额外信息的注解处理器。实现如下效果：
 *     1. 只有初始化参数，name 和 age 满足约束条件的时候，才能成功创建 Student 对象；
 *     2. 否则，拒绝创建，并抛出异常（根据约束信息，要实现的特殊效果）。
 */
public class StudentFactory {

    /**
     * 目标 Class 对象，因为注解实例放在类定义中使用
     */
    private Class studentClz;

    public StudentFactory() {
        // 操作对象为 Student，因此直接获取
        this.studentClz = Student.class;
    }

    /**
     * 其他人可以通过调用该方法获取新创建的 Student 对象，同时该方法接收待创建对象的两个初始化参数
     * @param age
     * @param name
     * @return
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public Student getStudent(int age, String name)
            throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 判断参数值，如果不满足约束，直接抛出异常
        judgeAge(age);

        judgeNameLength(name);

        // 通过反射创建 Student 对象，并且用合法的参数值初始化该对象，并返回
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

        // isAnnotationPresent 判断在成员变量中是否包含目标类型的注解信息
        if (ageField.isAnnotationPresent(AgeConstraint.class)) {

            // 获取指定类型的注解实例
            AgeConstraint ageAnnotation = ageField.getAnnotation(AgeConstraint.class);

            // 要获取哪个数值，就在注解实例上调用那个属性对应的方法
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
```

<!-- tabs:end -->


## 元注解

运行程序后，如果不在注解上注解 `@Retention(RetentionPolicy.RUNTIME)`，会发现定义了注解处理器，这和注解的保存级别有关。

和 Java 程序一致，注解有 3 种保留级别：
- SOURCE：注解将被编译器丢弃。

- CLASS：注解在 class 文件中可用，但会被 JVM 丢弃（内存没有）。

- RUNTIME：JVM 在运行时也会保留注解信息。

!> 注解默认情况下的保留级别是 CLASS，运行时已经消失！

元注解：注解的注解。  

Java 中提供了 4 种元注解，我们最常用的是其中 2 个。

### @Rentention

@Rentention 元注解，来定义我们自己定义的注解的保留级别。
- `RetentionPolicy.SOURCE`

- `RetentionPolicy.CLASS`

- `RetentionPolicy.RUNTIME`

示例：  
```java
@Retention(RetentionPolicy.RUNTIME)
@interface AgeConstraint {
    int maxAge();
    int minAge();
}
```

### @Target

@Target 元注解，注解可以作用的目标。
- 给整个类添加注解。

- 给类中成员变量添加注解。

- 给类中的构造方法添加注解。

- 给类中的普通方法添加注解。
  
示例：  

```java
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
@interface MyAnnotation {
  String name();
  OtherAnnotation test();
}
```

### @Documented

拥有这个注解的元素可以被 javadoc 此类的工具文档化。它代表着此注解会被 javadoc 工具提取成文档。在 doc 文档中的内容会因此注解的信息内容不同而不同。

### @Inherited

拥有此注解的元素其子类可以继承父类的注解。