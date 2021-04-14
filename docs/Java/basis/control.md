一个 Java 程序，通常是由多条符合 Java 语法规则的语句组成的。一个 Java 程序的执行，一定意味着多条 Java 语句的执行。  
既然是多条 Java 语句的执行，执行的结果就一定与语句执行的顺序有关，同样的语句不同的执行顺序，结果可能不同。

## 顺序结构

顺序结构描述的是 Java 语句之间，从上到下（或从左到右）依次执行的执行顺序。  

顺序结构是程序中最简单最基本的流程控制，没有特定的语法，就是按照代码书写的顺序执行。

## 选择结构
 
Java 中，选择结构有 2 种实现形式，if 语句 和 switch 语句

### if 语句  

if 语句实现选择结构有 3 种格式：  
- 如果，就（单分支）。格式：    
  ```java
  if (关系表达式) {
      语句体
  }
  ```  

- 非此即彼（两分支）。格式：  
  ```java
  if (关系表达式) {
      语句体1
  } else {
      语句体2
  }
  ```

- 多选一（多分支）。格式：  
  ```java
  if (关系表达式1) {
      语句体1
  } else  if (关系表达式2) {
      语句体2
  }
  …
  else {
      语句体n+1
  }
  ```

!> 在绝大部分场景下，三目运算符和 if 双分支选择结构，都可以相互替代。但是，如果选择结构执行的仅仅只是一个操作（没有返回值），此时三目运算无法替代 if 双分支选择结构。  

### switch 语句

switch 语句格式：  
```java
switch(表达式) {
    case 值1:
        语句体1
        break;
    case 值2:
        语句体2
        break;
    …
    default:	
        语句体 n+1
        break;
}
```

switch 格式解释：  
- switch 关键字：表示这是一个 switch 语句。

- switch 后的表达式：表示选择条件。

- case 语句：每个 case 语句表示一个分支。

- beak 语句：表示结束 switch 语句。

- default 语句：表示当所有其他的 case 的分支都不能匹配 switch 后表达式的值的时候，此时就会执行 default 分支。

注：  
- switch 语句后，表达式结果的取值范围：byte、short、int、char、枚举、String。

- case 后面的值，不能相同，且必须是和表达式值的类型相同的常量值。

- break 在语法上可以省略，省略之后，有可能触发多个 case 分支。

- default 语法上可以省略，但建议一般不要省略。

## 循环结构

与顺序结构和选择结构不同，循环结构的主要功能是控制 Java 语句能够重复执行。

循环结构，从语法上来讲，有 3 种实现形式：for 语句、while 语句和 do while 语句。  
  
不管哪种实现形式，都由 4 部分组成：  
- 初始化语句。  

- 条件判断语句。  

- 循环体语句。  

- 循环控制语句。

### for 语句

for 循环语句格式：
```java
for (初始化语句; 判断条件语句; 控制条件语句) {
    循环体语句
}
```

执行流程：  
![for 执行流程](./img/p15.png)

### while 语句

while 循环语句格式：  
```java
初始化语句
while (条件判断语句) {
    循环体语句
}
```

执行流程：
  和 for 循环一模一样。

### do while 语句

do while 循环语句格式：  
```java
初始化语句
do {
    循环体语句
} while (条件判断)
```

执行流程：  
do while 循环结构与 while 循环结构比，略有不同。不同之处在于 do while 中的循环体语句是在条件判断之前执行。

### for each 语句

Java 中还有一种功能很强的循环结构，for each 循环。可以用来依次处理数组中的每个元素，而不必为指定下标值而分心。格式：
```java
for (循环变量 : 数组) {
    循环体语句
}
```  
示例：
```java
for (int num : nums) {
    System.out.println(num);
}
```

for each 中的循环变量会遍历数组中的每一个元素，且不需要下标值。

## 跳转控制语句

通过跳转控制语句可以实现对循环结构更加细粒度的控制。
- break：中断。

- continue：继续。 

- return：返回。

### break

break 的使用场景：
- 在选择结构的 switch 语句中：结束 switch 语句。

- 在循环语句中：  
  - 跳出单层循环（如果有多层循环，只跳出内层）。

  - 带标签的跳出（多重循环）。  
    格式：`标签名 : 循环语句`。  
    标签名要符合 Java 的命名规则。  
    示例：  
    ```java
    outer:  // 标签名
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if( j == 2) {
          break outer;
        }
      }
    }
    ```

- 离开使用场景的存在是没有意义的。  

### continue

continue 的使用场景：
  - 在循环语句中：  
    - 退出单层循环的一次迭代过程。

    - 也可以使用标签。

  - 离开使用场景的存在是没有意义的。

### return

return 关键字不是为了跳转出循环体。更常用的功能是结束一个方法（函数），也就是退出一个方法，跳转到上层调用的方法。