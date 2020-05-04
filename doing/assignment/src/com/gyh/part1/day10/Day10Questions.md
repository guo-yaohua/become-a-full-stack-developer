# Day10 Javaobject01-2&javaobject02
1. 创建两个类，分别用来表示长方形和正方形，同时定义所需的成员变量代表长方形或者正方形的边长，在两个类中分别定义两个方法，用来求对应形状的面积和周长，并定义相应的 get，set 方法，获取和改变方形和正方形的边长。

2. 在 com.cskaoyan.a 包下，定义一个名为 MyClass 的类如下：  
    ```java
    public class MyClass {
        public void hello() {
            System.out.println("a包");
        }
    }
    ```
    同时，在 com.cskaoyan.b 包下，一个类名也为 MyClass：
    ```java
    public class MyClass {
        public void hello() {
            System.out.println("b包");
        }
    }
    ```
    同时在com.cskaoyan.b包下定义一个 Test 类如下：
    ```java
    public class Test {
        public void static main(String[] args) {
        MyClass myClass = new MyClass();
            myClass.hello();
        }
    }
    ```
    毫无疑问，当执行 Test 中的 main 方法的时候输出的是: b包。  
    现要求，在不改变 Test main 方法中代码的情况下，让 main 方法运行之后输出 a 包，应该怎么做？

3. 定义一个 Student 类，并要求其他类在使用 Student 类的时候，最多只能创建 10 个 Student 类的对象，如何实现？

    提示：首先，要实现该功能，就不能让外部类直接使用 `new Student(...)` 的方式来创建对象。如何不能让其他类 `new Student(...)`，只需将 Student 类的所有构造方法的权限改为 private 即可。  
    接着，把创建 Student 对象的工作交给一个专门的方法去做（想想这个方法应该是怎样的方法）。

答：  
1. 
2. 用 `import com.cskaoyan.a.MyCalss;` 语句导入 a 包即可。

3. 