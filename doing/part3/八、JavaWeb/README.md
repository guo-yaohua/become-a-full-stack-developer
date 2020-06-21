# 八、JavaWeb

## 目录

- [八、JavaWeb](#八javaweb)
  - [目录](#目录)
  - [1. Tomcat](#1-tomcat)
    - [1.1 安装和使用](#11-安装和使用)
    - [1.2 Tomcat 的目录结构](#12-tomcat-的目录结构)
    - [1.3 Tomcat 的组成结构](#13-tomcat-的组成结构)
    - [1.4 虚拟目录映射](#14-虚拟目录映射)
      - [1.4.1 方式一](#141-方式一)
      - [1.4.2 方式二](#142-方式二)
    - [1.5 默认页面和端口](#15-默认页面和端口)
  - [2 Servlet](#2-servlet)
    - [2.1 概述](#21-概述)
    - [2.2 实现第一个 Servlet](#22-实现第一个-servlet)
      - [2.2.1 FirstServlet](#221-firstservlet)
      - [2.2.2 Servlet 执行流程](#222-servlet-执行流程)


## 1. Tomcat

### 1.1 安装和使用

第一步：进入 [官网](http://tomcat.apache.org)，选择相应版本，进入其下载界面。  

<div align="center">
<img src="./img/p1.png">
</div>

第二步：选择下载方式。  
- Linux 操作系统下载 tar.gz 文件。

- Windows 操作系统建议下载 zip 文件。

<div align="center">
<img src="./img/p2.png">
</div>

第三步：把下载的文件解压，解压后的文件放入工作区即可。

使用先需要保证环境变量中存在 `JAVA_HOME`。可以通过命令行下的：`echo %JAVA_HOME%` 检查。

检查是否安装成功：
1. 双击 `bin` 目录下的 `startup.bat` 文件;

2. 在浏览器访问 `http://localhost:8080`，如果安装成功界面会有提示。

3. `shutdown.bat` 关闭服务器。

### 1.2 Tomcat 的目录结构

bin：存放启动和关闭 Tomcat 的脚本文件。

conf：存放 Tomcat 服务器的各种配置文件。
- 推荐：修改 `./conf` -> `server.xml` -> connector -> port="8080" 为 "80"。

lib：存放 Tomcat 服务器的支撑 jar 包。

logs：存放 Tomcat 的日志文件。

temp：存放 Tomcat 运行时产生的临时文件。

webpack：Web 应用所在目录，即供外界访问的 Web 资源的存放目录。

work：Tomcat 的工作目录。


### 1.3 Tomcat 的组成结构

```xml
// ./conf/server.xml
<Server>
    <Service>
        <Connector/>
        <Engine>
            <Host>
                <Context/>
            </Host>
        </Engine>
    </Service>
</Server>
```

Server：代表整个 Servlet 容器的组件，是最顶层元素，可以包含一个或多个 Service 元素。  

Service：包含一个 Engine 元素以及一个或多个 Connector 元素，这些 Connector 元素共享同一个 Engine。

Connector：代表和客户端程序实际交互的组件，负责接受客户请求，以及向客户返回响应。

Engine：每个 Service 元素只能包含一个 Engine 元素，它处理在同一个 Service 中所有 Connector 接收到的客户请求。

Host：在一个 Engine 中可以包含多个 Host，它代表一个虚拟主机，它可以包含一个或者多个 Web 应用。

Context：使用最频繁的元素，代表了运行在虚拟主机上的单个 Web 应用。

### 1.4 虚拟目录映射

#### 1.4.1 方式一

在 `server.xml` 文件的 host 元素中配置 context 元素，例如：
```
<Host name="localhost"  appBase="webapps"
      unpackWARs="true" autoDeploy="true">

      <Context path="/test" docBase="D:\test"></Context>
```
并在 `D:\test` 下新建 `index.html` 文件。  

<div align="center">
<img src="./img/p3.png">
</div>

注：在 Tomcat6 中，不再建议在 `server.xml` 文件中配置 context 元素。

#### 1.4.2 方式二

Tomcat 自动映射：Tomcat 服务器会自动管理 webapps 目录下的所有 Web 应用，并把它映射成虚似目录。  
换句话说，Tomcat 服务器 webapps 目录中的 Web 应用，外界可以直接访问。

在 `./conf/Catalina/localhost` 目录下新建一个 xml 文件，文件名为虚拟目录名。  

示例：  
<div align="center">
<img src="./img/p4.png">
</div>

即可达到和方式一一样的效果。


### 1.5 默认页面和端口

默认页面：`./conf` -> `web.xml` -> welcome-file-list。

默认端口：`./conf` -> `server.xml` -> connector -> port。

## 2 Servlet

### 2.1 概述

> A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP, the HyperText Transfer Protocol. 

Servlet 是 Sun 公司提供的一门用于开发动态 Web 资源的技术。  

Sun 公司在其 API 中提供了一个 servlet 接口，用户若想开发一个动态 Web 资源（即开发一个 Java 程序向浏览器输出数据），需要完成以下 2 个步骤：
1. 编写一个 Java 类，实现 servlet 接口；

2. 把开发好的 Java 类部署到 Web 服务器中。

注：按照一种约定俗成的称呼习惯，通常我们也把实现了servlet 接口的 Java 程序，称之为 Servlet。

Servlet 在 Web 应用中的位置：
```
mail
|
|--html、jsp、css、js 文件等
|
|--WEB-INF
    |
    |--classes
    |   |
    |   |--Java 类
    |
    |--lib
    |   |
    |   |--Java 类运行所需要的 jar 包
    |
    |--web.xml
```

mail：Web 应用所在目录

html、jsp、css、js 文件等：这些文件一般存在 Web 应用根目录下，根目录下的文件外界可以直接访问。

WEB-INF：必须直接放在应用下一级目录里。Java 类、jar 包、Web 应用的配置文件存在这个目录下，该目录下的文件外界无法非法直接访问，由 Web 服务器负责调用。

### 2.2 实现第一个 Servlet

#### 2.2.1 FirstServlet

第一步：下载 Tomcat，在 Tomcat 的 `webapps` 文件夹下新建应用文件夹，如 `app1`。  
在 `app1` 文件夹下新建文件夹 `WEB-INF/classes`。

第二步：编译一个 Java 文件，将生成的 class 文件放入 `classes` 文件夹下。如：
```java
import javax.servlet.*;

public class FirstServlet extends GenericServlet {
    public void service(ServletRequest req, ServletResponse res) throws ServletException, java.io.IOException {
        res.getWriter().println("Hello World");
    }
}
```

使用命令行编译时，需要注意类加载机制：
```
javac -classpath xx/xx/xx/apache-tomcat-8.5.56/lib/servlet-api.jar FirstServlet.java
```

第三步：`WEB-INF` 下新建文件 `web.xml`，在 xml 文件中进行配置虚拟路径。如：
```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
  version="3.1"
  metadata-complete="true">


    <servlet>
      <servlet-name>hello</servlet-name>
      <servlet-class>FirstServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/servlet</url-pattern>
    </servlet-mapping>

</web-app>
```

第四步：启动 Tomcat，在默认端口下后缀 `/app1/servlet` 即可访问 Servlet 应用。  
<div align="center">
<img src="./img/p5.png">
</div>

#### 2.2.2 Servlet 执行流程

当访问 `http://localhost/app1/servlet` 时：
1. 地址栏打出该地址，浏览器构建一个请求报文；

2. 传输到目标机器，到达指定端口的 connector，然后 connector 接收到该请求，将请求报文转成 request 对象；

3. connector 同时还会生成一个空的 response 对象，然后将其和 request 对象传给 engine；
 
4. engine 接着选择 host，将这两个对象传给 host；

5. host 选择 `Context（/app1）`，将这两个对象传给 Context；

6. Context 在当前应用下去寻找 `/servlet`，如果找到，则往 response 对象里面写入对应的数据；

7. 这两个对象依次返回，给 connector；

8. connector 读取 response 对象里面的数据，然后生成一个响应报文，发送出去。

