# Day14 JavaObject02-2

1. a. 定义一个接口 Compute，用来完成计算器的功能，比如最简单的加减乘除功能。  
    ```java
    interface Compute {
    double compute(double a, double b)
    } 
    ```
   b. 定义一个 ShowCompute 类，里面定义一个工具方法 `compute(Compute com,double a, double b)`，该方法可以执行两个 double 类型数据的加减乘除（也就是需要定义不同的 Compute 接口的子类，实现具体的加减乘除功能，当执行该方法时，传递不同的子类对象，就可以完成不同的计算）。  
      编写一个测试类，通过调用 ShowCompute 类的 `compute(Compute com,double a, double b)` 方法来完成加减乘除功能
  
2. 根据注释填写 (1), (2),(3) 处的代码
```java
public class Test{
  public static void main(String[] args){
         // 初始化 bean1
         (1)
         bean1.i++;
         // 初始化 bean2
         (2)
         bean2.j++;
         //初始化 bean3
         (3)
         bean3.k++;
  }
  class Bean1{
         public int i = 0;
  }

  static class Bean2{
         public int j = 0;
  }
}

class Bean{
  class Bean3{
         public int k = 0;
  }
}
```

## 答

1. 见代码

2. (1)：Test.Bean1 bean1 = Test().new Bean1();  
   (2)：Bean2 bean2 = new Bean2();  
   (3)：Bean.Bean3 bean3 = new Bean().new Bean3;  