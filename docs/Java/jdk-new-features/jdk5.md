## 静态导入

导包：
- 必须导入到类这一级别。

- 作用：导入的类就好像定义在当前这个包下面。

静态导入：
- 必须导入到方法这一级别，并且必须是静态方法。

- 作用：导入的静态方法就好像定义在当前这个类中一样。

推荐：不要使用静态导入，可读性差

static 的用法有：
- 静态代码块：对类进行初始化，类加载的时候执行，并且只执行一次。

- 静态变量：该变量是类所有，被该类的所有成员共享。

- 静态方法。

- 静态内部类。

- 静态导入：导入静态方法。

示例：  
```java
import static java.lang.Math.*;

public class StaticImportDemo1 {
    public static void main(String[] args) {

        System.out.println(sqrt(1.0));  // 不需要使用前缀 Math.
        System.out.println(abs(-100));
        System.out.println(max(2, 3));
    }
}
```


## 泛型

泛型类：
- 泛型定义在类上面。

- 作用域：整个类。

- 格式：`public class 类名<泛型类型1,…>`。

泛型的命名规则：必须满足标识符的规则。  
业界规范：一般用大写字母表示。
- T：type。

- E：element。

- K：key。

- V：value。

泛型方法：
- 把泛型定义在方法上面。

- 作用域：方法签名上或者方法体内。

- 格式：`public <泛型类型> 返回类型 方法名(泛型类型...)`。

注：
- 因为返回值类型也可以是泛型，泛型必须先定义才能使用，所以泛型要定义在返回值类型的前面

- 有泛型方法的类不一定是泛型类吗。

泛型的好处：
- 提高了程序的安全性。

- 将运行期遇到的问题转移到了编译期。

- 省去了类型强转的麻烦。

设计原则：及早失败原则。

掌握要求：
- 可以利用泛型操作集合。

- 能够看懂别人些的泛型代码。

泛型通配符：提供类似数组的功能，但是不要引入数组中可能出现的问题。
- 泛型通配符<?>  
  任意类型，如果没有明确，那么就是 Object 以及任意的 Java 类了。

- ? extends E
  向下限定，E 及其子类。

- ? super E
  向上限定，E 及其父类。

泛型擦除：编译器在编译的过程中，会把泛型信息「擦除」。
- E --> Object

- ? extends Animal --> Animal

- ? super Animal --> Object

## 可变长参数

作用：定义方法的时候不知道该定义多少个参数，我们就可以使用可变长参数。

本质：其实就是一个数组。

格式: `修饰符 返回值类型 方法名(数据类型…  变量名){}`。

示例：  
```java
public class TestDemo {
    public static int add(int... arr) {
        int sum = 0;
        for(int i : arr) sum += i;
        return sum;
    }
    
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = 3;
        int sum = add(a, b, c);
        System.out.println(sum);
    }
}

/* 运行结果：
6
*/
```

注：可变长参数只能位于最后。因此一个方法中最多有 1 个可变长参数.如果一个方法中需要多个可变长参数，只能依靠传入数组。

## 增强 for 循环

好处：简化数组和 Collection 集合的遍历。

坏处：
- 没有索引信息。

- 不能够修改数组和集合，只能查找元素。

格式：
```java
for(元素数据类型 变量 : 数组或者 Collection 集合) {
    代码块
}
```

本质：for each 对数组进行特殊处理, 会用迭代器遍历其它对象。  

注：数组和实现了 Iterable 接口的对象才可以使用 for each 循环。



