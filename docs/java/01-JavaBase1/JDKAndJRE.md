### JRE 与 JDK

JRE（JAVA  Runtime Environment） 是 Java 程序的运行时环境，包含 JVM 和运行时所需要的核心类库。

JDK（JAVA  Develop kit) 是 Java 程序开发工具包，包含 JRE 和开发人员使用的工具。

<div align="center">
<img src="./img/p18.png">
</div>

JDK的下载和安装：[JDK8 下载地址](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)。  
> 建议 JDK 安装路径中不要有中文和空格。

PATH 环境变量是操作系统的环境属性，告诉操作系统可执行程序（javac）路径。  

官方推荐的配置 PATH 环境变量方式：
1. 先配置 JAVA_HOME 环境变量：`xx\xx\xx\jdk1.8`；

2. 再添加 PATH 环境变量：`$JAVA_HOME$\bin`。