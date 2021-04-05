Java 程序运行原理：

![java程序运行原理](./img/p2.png)

## 命令行方式

第一步：创建 `HelloWorld.java` 文件，用文本编辑器打开编辑：

```java
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}  
```  

第二步：打开 cmd，进入 `HelloWorld.java` 文件所在的文件夹，执行命令：

```
javac HelloWorld.java
```

之后会生成 `HelloWorld.class` 文件，接着执行：

```
java HelloWorld
``` 

即可在命令行打印出
```
HelloWorld!
```

如果打印内容中包含中文，cmd 命令行下会显示乱码。用 javac 命令时添加 `-enconding UTF-8` 即可解决。如：  
```bash
javac -encoding UTF-8 HelloWorld.java
```

## IDEA方式

[IDEA 官网地址](https://www.jetbrains.com/idea)，选择版本，下载安装即可。  

安装完成后，打开 IDEA，选择新建项目：

![](./img/p4.png ':size=560x350')

选择创建 Java 项目，并选择 JDK 的版本：

![](./img/p5.png ':size=560x350')

此处不必勾选：

![](./img/p6.png ':size=560x350')

设置项目名称和存储位置：

![](./img/p7.png ':size=560x350')

Java 代码一般存放在 src 目录下，在此新建一个包：

![](./img/p8.png ':size=560x350')

给包命名：

![](./img/p9.png)

右击包名，在包文件夹下新建 Java 程序：

![](./img/p10.png ':size=560x350')

给程序命名：

![](./img/p11.png)

写好代码后，右击侧边小三角，选择运行，即可在下方显示运行结果：

![](./img/p12.png)

![](./img/p13.png ':size=560x350')