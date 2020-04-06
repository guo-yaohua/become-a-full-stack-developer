# week1

本周学习任务：  
- Java语言介绍 、JDK 的介绍，Java 开发环境配置
- 常见的基本操作，快捷键，基本 dos 命令
- 基本语法，包括关键字、标识符、常量、进制转换、数据类型、运算符、流程控制语句
- 一维数组，二维数组，多维数组
- 方法，方法重载，多态的引入

## 目录

- [Java语言介绍](#Java语言介绍)  
  - [JAVA 语言的诞生](#JAVA语言的诞生)
  - [JAVA 语言的平台版本](#JAVA语言的平台版本)
  - [JAVA 语言的特点](#JAVA语言的特点)
  - [JDK 与 JRE](#JDK与JRE)
  - [JDK 的下载和安装](#JDK的下载和安装)
  - [JAVA 程序运行原理](#JAVA程序运行原理)
  - [PATH 和 CLASSPATH 环境变量](#PATH和CLASSPATH环境变量)

## Java语言介绍

### JAVA语言的诞生

Java 是由 Sun 公司于 1995 年 5 月推出的 Java 面向对象程序设计语言和 Java 平台的总称。由 James Gosling 和同事们共同研发，并在 1995 年正式推出。  

### JAVA语言的发展

- JDK 1.0 (1996.1)
- JDK 1.1 (1997.2)
- J2SE 1.2 (1998.12) 
- J2SE 1.3 (2001.9)
- J2SE 1.4 (2002.2) 
- J2SE 5.0 (2004.9) 
- Java SE 6 (2006.12) 
- Java SE 7 (2011.7) 
- Java SE 8 (2014.3) 
- Java SE 9 (2017.9)
- Java SE 10 (2018.3)
- Java SE 11 (2018.9)
- Java SE 12 (2019.3)
- Java SE 13 (2019.9)
- Java SE 14 (2020.3)

### JAVA语言的平台版本

- Java SE 标准版。  
  是为开发普通桌面和商务应用程序提供的解决方案  

- Java ME 嵌入式开发  
  是为开发电子消费产品和嵌入式设备提供的解决方案  

- Java EE 企业级开发  
  是为开发企业环境下的应用程序提供的一套解决方案  

注：Java SE 为整个技术架构的核心。  

### JAVA语言的特点

- **跨平台**  
  Java 程序是在 Java 虚拟机上运行，而非直接运行于操作系统。因此通过 Java 语言编写的应用程序在不同的系统平台上都可以运行。  
  ![跨平台](./img/p1.png)  

- 面向对象  

- 解释型  
  编译型语言写出的代码，首先通过编译器的编译，全部转化成目标代码，然后依次在操作系统中执行。解释型语言则是转化一句，执行一句。  
  Java 编译过程：.java -> .class -> Java 虚拟机。  

- 健壮  
  提供了异常机制。  

- 动态  
  两个方面  
  1. 在 Java 语言中，可以简单、直观地查询运行时的信息。  
  2. 可以将新代码加入到一个正在运行的程序中。  

- 分布式  
  Java 语言具有强大的、易于使用的联网能力。

- 高效  
  由于 Java 是一种解释型语言，所以它的执行效率相对就会慢一些，但由于 Java 语言采用以下两种手段，使其拥有较好的性能。  
  1. Java 语言源程序编写完成后，先使用 Java 伪编译器进行伪编译，将其转换为中间码，再解释。  
  2. Java 语言提供一种「准实时」（Just-in-Time，JIT）编译器，当需要更快速度时，Java 语言可以使用 JIT 编译器将字节码转换成机器码，然后将其缓冲下来，这样速度就会更快。

- 多线程

- 结构中立（字节码）  
  Java 编译器通过伪编译后，将生成一个与任何计算机体系统无关的「中性」的字节码。  

- **开源**

### JDK与JRE

- JRE(JAVA  Runtime Environment)  
  包括 Java 虚拟机、运行时核心类库（rt.jar）。JRE 主要是给已经写好的 Java 程序使用，换句话说 Java 程序要能在操作系统中运行，必须有 JRE。

- JDK(JAVA  Develop kit)  
  首先，JDK 包含 JRE，除此之外，JDK 中还包含一些供开发者使用的工具，比如 Javac、Javap 等。

### JDK的下载和安装

JDK8 下载地址：https://www.oracle.com/java/technologies/javase-jdk8-downloads.html。  
建议 JDK 安装路径中不要有中文，同时当提示安装 JRE 时，可以选择不安装。

### JAVA程序运行原理

![JAVA程序运行原理](./img/p2.png)  

### PATH和CLASSPATH环境变量

PATH 环境变量是操作系统的环境属性，告诉操作系统可执行程序（javac）路径。  
官方推荐的配置 PATH 环境变量方式：  
1. JAVA_HOME 环境变量：xx\xx\xx\jdk1.8  
2. 配置 PATH 环境变量：$JAVA_HOME$\bin

CLASSPATH 环境变量是所有 *.class 文件的执行路径，给 JVM 用，告诉 JVM 到哪里加载 *.class 文件。  


<!--
## 基础

Java中的byte，short，char进行计算时都会提升为int类型。
-->