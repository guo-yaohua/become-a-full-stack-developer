# Day08 JavaObject01
1. 定义一个Student类：   
    1. 要求包含 4 个成员变量 String name, int age, boolean isMale, int sno  
    2. 分别定义5个构造方法。分别为
        - 无参构造
        - 初始化 name成员变量值的 1 参构造方法
        - 初始化 name 和 sno 两个成员变量值的 2 参构造方法
        - 初始化 name，age，isFimale 三个成员变量值的 3 参构造方法
        - 初始化 name，age，isMale，sno这四个成员变量值的 4 参构造方法
    3. 在定义一个成员方法  
        ```java
        public void print() {
            System.out.println(name + "---" + age + "---" + (isMale ? "男" : "女") + "---" + sno);
        }
        ```

2. 在第一题的基础上，创建两个 Student 对象分别为 stu1 和 stu2。
   - 要求 stu1 对象的四个成员变量 name, age, isMale, sno 的值分别为 “张三”,18,true, 1
   - 要求 stu2 对象的四个成员变量 name, age, isMale, sno 的值分别为 “李四”,25,false, 2
   - 然后在两个对象上调用 print() 方法即 `stu1.print(), stu2.print()`，两次调用方法输出的结果相同吗？为什么？(提示：在方法体中访问 name 成员变量，直接用成员变量名 name，是 this.name 的简写形式)。

3. 在定义类 MyStudent（类中定义的成员变量和成员方法同 Student，同时在 MyStudent 类中定义和 Student 相似的 5 个构造方法）的基础上，根据需要修改 MyStudent 相应构造方法的方法体，要求实现
    ```java
    MyStudent st = new MyStudent();
    st.print();
    ```
    这两句代码打印出的结果是 麻花疼---100---男---1000

4. 如图所示，分别将一个 double 类型的数据和一个引用类型的数据传入某个方法，在方法内分别对对应的数据进行一些运算 ，经过运算之后，double 类型的数据和引用类型的数据分别是什么? 并解释下，为什么。  
![1](./img/t1.png)  
![2](./img/t2.png)

答：  
1.  
2. 不同。stu1 和 stu2 指向两片不同的内存空间地址。`对象名.方法` 会向方法传递对象名的内存空间地址，因此会按各自地址上的属性值执行方法语句，所以结果不同。  
3.   
4. 输出：
```
传入的person的name：我是马化腾
方法内重新赋值后的name：我是马云
方法执行后的money：100.00
方法执行后的name：我是马云
```
原因：name 发生改变是因为 p 作为一个引用类型对象变量名存储在栈中，保存的是堆中一块内存的地址数值。当其作为方法的参数时，传递的也是地址数值。一块堆内存空间地址可以同时被多个栈内存共同指向。执行方法时，方法中的形参也会指向该地址，则通过形参也可以修改这片内存空间的属性值。  
money 不改变是因为它是一个局部变量，作为实参传递的是自己指向的内存空间对应的变量值。