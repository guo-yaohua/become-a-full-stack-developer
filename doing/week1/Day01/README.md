# Day01 Java概述
作业  
1：独立编写 Hello World 程序。  
要求记事本完成，如果遇到错误，记录错误信息。  
2：Java 语言是跨平台的吗?JVM 是跨平台的吗?  
3：设置环境变量使 QQ 或者微信可以在任意目录下，通过命令行执行。

答：  
1.  
```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
```  
![Q1](./Q1.png)    
2.  
Java 语言是跨平台的，JVM 不是跨平台的。  
3.  
添加环境变量：  
![Q3](./Q3a.png)  
或命令行设置：`set path=C:\MyPrograms\Tencent\QQ\Bin`  
![Q3](./Q3b.png)