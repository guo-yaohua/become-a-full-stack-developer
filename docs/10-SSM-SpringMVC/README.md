# 十、SpringMVC

## 目录

## 1 SpringMVC

### 1.1 SpringMVC 概述

SpringMVC：
- Spring 提供的 MVC 框架。

- 是 Spring 框架的一部分，负责表示层。


目标是消灭 servlet：提供了一个统一的处理器 DispatcherServlet。 


SpringMVC 架构：
<div align="center">
<img src="./img/p1.png"><br>
<img src="./img/p2.png">
</div

### 1.2 Web 应用的目录结构

#### 1.2.1 服务器下的目录结构

```
webroot 应用根目录
|
|--html、jsp、css、js 文件等
|
|--WEB-INF
|    |
|    |--classes
|    |   |
|    |   |--classpath：编译后的字节码文件和配置文件
|    |
|    |--lib
|    |   |
|    |   |--Java 类运行所需要的 jar 包（pom 文件中 scop 为 compiled、runtime）
|    |
|    |--web.xml
|    |
|    |--不能直接访问的视图文件、静态资源
```

#### 1.2.2 IDEA 中的目录结构

```
mudule
|
|--src
|   |--main
|   |
|   |   |
|   |   |--java
|   |   |
|   |   |--reources
|   |   |
|   |   |--webapp
|   |   |   |
|   |   |   |--WEB-INF
|   |   |   |    |
|   |   |   |    |--web.xml
|   |   |   |    |
|   |   |   |    |--不能直接访问的视图文件、静态资源
|   |   |   |
|   |   |   |--html、jsp、css、js 文件等
|   |
|   |--test
|
|--pom.xml
```