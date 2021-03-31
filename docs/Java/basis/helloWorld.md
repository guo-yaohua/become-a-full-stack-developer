Java 程序运行原理：  
<div align="center">
<img src="./Java/basis/img/p2.png">
</div>

#### 命令行方式

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
```
javac -encoding UTF-8 HelloWorld.java
```

#### IDEA方式

[IDEA 官网地址](https://www.jetbrains.com/idea)，选择版本，下载安装即可。  

安装完成后，打开 IDEA，选择新建项目：  
<div align="center">
<img style="width:600px" src="./Java/basis/img/p4.png">
</div>

选择创建 Java 项目，并选择 JDK 的版本：  
<div align="center">
<img style="width:600px" src="./Java/basis/img/p5.png">
</div>

此处不必勾选：  
<div align="center">
<img style="width:600px" src="./Java/basis/img/p6.png">
</div>

设置项目名称和存储位置：  
<div align="center">
<img style="width:600px" src="./Java/basis/img/p7.png">
</div>

Java 代码一般存放在 src 目录下，在此新建一个包：  
<div align="center">
<img style="width:600px" src="./Java/basis/img/p8.png">
</div>

给包命名：  
<div align="center">
<img src="./Java/basis/img/p9.png">
</div>

右击包名，在包文件夹下新建 Java 程序：  
<div align="center">
<img style="width:600px" src="./Java/basis/img/p10.png">
</div>

给程序命名：  
<div align="center">
<img src="./Java/basis/img/p11.png">
</div>

写好代码后，右击侧边小三角，选择运行，即可在下方显示运行结果：  
<div align="center">
<img style="width:600px"  src="./Java/basis/img/p12.png">
<br/>
<img style="width:600px"  src="./Java/basis/img/p13.png">
</div> 