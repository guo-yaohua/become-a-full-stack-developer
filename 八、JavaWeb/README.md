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
    - [2.3 Servlet 接口实现类](#23-servlet-接口实现类)
    - [2.4 IDEA 开发 Servlet](#24-idea-开发-servlet)
    - [2.5 Servlet 的生命周期](#25-servlet-的生命周期)
    - [2.6 url-pattern 细节](#26-url-pattern-细节)
      - [2.6.1 多个映射关系](#261-多个映射关系)
      - [2.6.2 两个特殊的 url-pattern](#262-两个特殊的-url-pattern)
    - [2.7 ServletConfig](#27-servletconfig)
    - [2.8 ServletContext](#28-servletcontext)
      - [2.8.1 全局性初始化参数](#281-全局性初始化参数)
      - [2.8.2 全局性共享数据](#282-全局性共享数据)
      - [2.8.3 获取绝对路径](#283-获取绝对路径)
  - [3 ServletRequest](#3-servletrequest)
    - [3.1 Request 常用方法](#31-request-常用方法)
      - [3.1.1 获取请求行](#311-获取请求行)
      - [3.1.2 获取请求头](#312-获取请求头)
      - [3.1.3 获取请求参数](#313-获取请求参数)
    - [3.2 中文乱码问题](#32-中文乱码问题)
    - [3.3 form 表单路径写法](#33-form-表单路径写法)
    - [3.4 转发和包含](#34-转发和包含)
      - [3.4.1 转发](#341-转发)
      - [3.4.2 包含](#342-包含)
  - [4 ServletResponse](#4-servletresponse)
    - [4.1 输出数据到客户端](#41-输出数据到客户端)
    - [4.2 中文乱码问题](#42-中文乱码问题)
    - [4.4 刷新和重定向](#44-刷新和重定向)
      - [4.4.1 定时刷新](#441-定时刷新)
      - [4.4.2 重定向](#442-重定向)
      - [4.4.3 转发、定时刷新、重定向区别联系](#443-转发定时刷新重定向区别联系)
    - [4.5 下载](#45-下载)
  - [5 FileUpload](#5-fileupload)
    - [5.1 Web 页面添加上传输入项](#51-web-页面添加上传输入项)
    - [5.2 Servlet 读取上传文件](#52-servlet-读取上传文件)
    - [5.3 文件存放位置](#53-文件存放位置)
    - [5.4 文件上传案例](#54-文件上传案例)
    - [5.5 保存数据到 bean](#55-保存数据到-bean)
      - [5.5.1 中规中矩版](#551-中规中矩版)
      - [5.5.2 优化版](#552-优化版)
    - [5.6 文件保存问题](#56-文件保存问题)
      - [5.6.1 文件重名](#561-文件重名)
      - [5.6.2 单个目录文件数过多](#562-单个目录文件数过多)
    - [5.7 保存回显操作](#57-保存回显操作)
  - [6 Cookie](#6-cookie)
    - [6.1 Cookie 概述](#61-cookie-概述)
    - [6.2 Cookie 案例](#62-cookie-案例)
      - [6.2.1 案例 1](#621-案例-1)
      - [6.2.2 案例 2](#622-案例-2)
    - [6.3 Cookie 设置](#63-cookie-设置)
      - [6.4.1 设置 Maxage](#641-设置-maxage)
      - [6.4.2 设置 path](#642-设置-path)
      - [6.4.3 设置 domain](#643-设置-domain)
  - [7 Session](#7-session)
    - [7.1 Session 概述](#71-session-概述)


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

### 2.3 Servlet 接口实现类

Servlet 接口定义了两个默认实现类：GenericServlet、HttpServlet。

HttpServlet 指能够处理 HTTP 请求的 Servlet，它在原有 Servlet 接口上添加了一些与 HTTP 协议处理方法，它比 Servlet 接口的功能更为强大。  
因此，开发人员在编写 Servlet 时，通常应继承这个类，而避免直接去实现 Servlet 接口。

HttpServlet 在实现 Servlet 接口时，覆写了 service 方法，该方法体内的代码会自动判断用户的请求方式，如为 GET 请求，则调用 HttpServlet 的 doGet 方法，如为 Post 请求，则调用 doPost 方法。  
因此，开发人员在编写 Servlet 时，通常只需要覆写 doGet 或 doPost 方法，而不要去覆写 service 方法。

### 2.4 IDEA 开发 Servlet

第一步：创建 Project 和 Module。  
<div align="center">
<img src="./img/p6.png"></br>
<img src="./img/p7.png">
</div>

第二步：创建 Web 应用。  

<div align="center">
<img src="./img/p8.png"></br>
<img src="./img/p9.png"></br>
<img src="./img/p10.png">
</div>

第三步：Debug，然后就可以在浏览器访问。  
<div align="center">
<img src="./img/p11.png"></br>
<img src="./img/p12.png">
</div>


### 2.5 Servlet 的生命周期

Servlet 是一个供其他 Java 程序（Servlet 引擎）调用的 Java 类，它不能独立运行，它的运行完全由 Servlet 引擎来控制和调度。

针对客户端的多次 Servlet 请求，通常情况下，服务器只会创建一个 Servlet 实例对象，也就是说 Servlet 实例对象一旦创建，它就会驻留在内存中，为后续的其它请求服务，直至 Web 容器退出（或应用停止），Servlet 实例对象才会销毁。

在 Servlet 的整个生命周期内，Servlet 的 init 方法只被调用一次。而对一个 Servlet 的每次访问请求都导致 Servlet 引擎调用一次 Servlet 的 service 方法。对于每次访问请求，Servlet 引擎都会创建一个新的 HttpServletRequest 请求对象和一个新的 HttpServletResponse 响应对象，然后将这两个对象作为参数传递给它调用的 Servlet 的 service() 方法，service 方法再根据请求方式分别调用 doXXX 方法。 

注：默认情况下，init 会在第一次调用该 Servlet 的时候执行，但是也可以通过添加相应的参数（设置 load-on-startup 为非负数），使其在应用被加载的时候执行。


### 2.6 url-pattern 细节

#### 2.6.1 多个映射关系

同一个 Servlet 可以被映射到多个 URL 上，即多个 `<servlet-mapping>` 元素的 `<servlet-name>` 子元素的设置值可以是同一个 Servlet 的注册名。  

在 Servlet 映射到的 URL 中也可以使用 `*` 通配符，但是只能有两种固定的格式：
- 一种格式是 `*.扩展名`。

- 一种格式是以正斜杠 `/` 开头并以 `/*` 结尾。

优先级：
- `/*` 的优先级要高于 `*.do`。

- 以 `/` 开头的优先级要高于 `*.后缀` 的优先级。

- 如果多个 `/` 开头的 url-pattern 同时满足，那么匹配的程度越高，越优先执行谁。

如以下的映射关系：  
Servlet1 映射到 `/abc/*`；   
Servlet2 映射到 `/*`；   
Servlet3 映射到 `/abc`；   
Servlet4 映射到 `*.do`。   

则：
- 当请求 URL 为 `/abc/a.html`，`/abc/*` 和`/*` 都匹配，Servlet 引擎将调用 Servlet1。

- 当请求 URL 为 `/abc` 时，`/abc` 和 `/*` 都匹配，Servlet 引擎将调用 Servlet3。

- 当请求 URL 为 `/abc/a.do` 时，`/abc/*` 和 `*.do` 和 `/*` 都匹配，Servlet 引擎将调用 Servlet1。

- 当请求 URL 为 `/a.do` 时，`/*` 和 `*.do` 都匹配，Servlet 引擎将调用 Servlet2。

- 当请求 URL 为 `/xxx/yyy/a.do` 时，`/*` 和 `*.do` 都匹配，Servlet 引擎将调用 Servlet2。

#### 2.6.2 两个特殊的 url-pattern

如果某个 Servlet 的映射路径仅仅为一个正斜杠 `/`，那么这个 Servlet 就成为当前 Web 应用程序的缺省 Servlet。  
凡是在 `web.xml` 文件中找不到匹配的 `<servlet-mapping>` 元素的 URL，它们的访问请求都将交给缺省 Servlet 处理。也就是说，缺省 Servlet 用于处理所有其他 Servlet 都不处理的访问请求。 

在 `tomcat/conf/web.xml` 文件中，注册了一个名称为 `org.apache.catalina.servlets.DefaultServlet` 的 Servlet，并将这个 Servlet 设置为了缺省 Servlet。  
当访问 Tomcat 服务器中的某个静态 HTML 文件和图片时，实际上是在访问这个缺省 Servlet。 

### 2.7 ServletConfig

在 Servlet 的配置文件中，可以使用一个或多个 `<init-param>` 标签为某个 Servlet 配置一些初始化参数。

当 Servlet 配置了初始化参数后，Web 容器在创建 Servlet 实例对象时，会自动将这些初始化参数封装到 ServletConfig 对象中，并在调用 `Servlet` 的 init 方法时，将 ServletConfig 对象传递给 Servlet。进而，通过 ServletConfig 对象就可以得到当前 Servlet 的初始化参数信息。

示例：
```xml
<servlet>
  <servlet-name>config</servlet-name>
  <servlet-class>com.gyh.test</servlet-class>
  <init-param>
    <param-name>name</param-name>
    <param-value>zhang3</param-value>
  </init-param>
</servlet>
```
```java
ServletConfig servletConfig = getServletConfig();
String name = servletConfig.getInitParameter("name");
System.out.printf(name);

// 即可打印 name 的 value
```

### 2.8 ServletContext

#### 2.8.1 全局性初始化参数

示例：
```xml
<context-param>
  <param-name>key</param-name>
  <param-value>utf-8</param-value>
</context-param>
```
```java
String key = getServletContext().getInitParameter("key");
System.out.println(key);
```

#### 2.8.2 全局性共享数据


Context 类下的三个数据操作：
- `setAttribute(key,value)`。

- `getAttribute(key)`。

- `removeAttribute(key)`。

示例：记录网站历史访问次数。  
```java
ServletContext servletContext = getServletContext();
synchronized (servletContext){
    Integer count = (Integer) servletContext.getAttribute("count");
    if(count == null){
        count = 0;
    }
    servletContext.setAttribute("count", ++count);
}
response.getWriter().println("history total count: " + servletContext.getAttribute("count"));
```

#### 2.8.3 获取绝对路径

Context 关于路径的两个操作：
- `getRealpath(String path)`：获取绝对路径。  
  path 为文件和部署根目录的相对路径关系。  

  注：该方法也可以获取 `WEB-INF` 目录下的文件。


## 3 ServletRequest

Web 服务器收到客户端的 http 请求，会针对每一次请求，分别创建一个用于代表请求的 Request 对象、和代表响应的 Response 对象。   
- Request：获取客户机（浏览器）提交过来的数据。

- Response：向浏览器（客户端）输出数据。

### 3.1 Request 常用方法

HttpServletRequest 对象代表客户端的请求，当客户端通过 HTTP 协议访问 http 服务器时，HTTP 请求头（正文）中的所有信息都封装在这个对象中，开发人员通过这个对象的方法，可以获得客户这些信息。

#### 3.1.1 获取请求行

`getMethod`：请求方法。

`getRequestURL`：URL。

`getRequestURI`：资源名。

`getProtoco`：协议 / 版本。

#### 3.1.2 获取请求头

`getRemoteAddr`：方法返回发出请求的客户机的 IP 地址。

`getRemoteHost`：方法返回发出请求的客户机的完整主机名。

`getRemotePort`：方法返回客户机所使用的网络端口号。

`getLocalAddr`：方法返回 WEB 服务器的 IP 地址。

`getLocalName`：方法返回 WEB 服务器的主机名。

#### 3.1.3 获取请求参数

`getParameter(name)`。

`getParameterValues(String name)`。

`getParameterNames`。

`getParameterMap()`。

示例：获取 form 表单参数。  
form 表单：
```xml
<form action="/app/submit" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="password"><br>
    性别：男<input type="radio" name="gender" value="male">
          女<input type="radio" name="gender" value="female"><br>
    爱好：java<input type="checkbox" name="hobby" value="java">
          c++<input type="checkbox" name="hobby" value="c++">
          python<input type="checkbox" name="hobby" value="python"><br>
    简介<textarea name="description"></textarea><br>
    <input type="submit">
</form>
```
获取方式一：
```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String gender = request.getParameter("gender");
    String[] hobbies = request.getParameterValues("hobby");
    String description = request.getParameter("description");

    // ...
}
```
获取方式二：
```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Enumeration<String> parameterNames = request.getParameterNames();
    while (parameterNames.hasMoreElements()){
        String name = parameterNames.nextElement();
        String[] values = request.getParameterValues(name);
        if(values.length == 1){
            System.out.println(name + " = " + values[0]);
        }else if(values.length > 1){
            System.out.println(name + " = " + Arrays.toString(values));
        }
    }
}
```
获取方式三：
```java
// 将参数信息封装到对象
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setCharacterEncoding("utf-8");
    Map<String, String[]> parameterMap = request.getParameterMap();

    User user = new User();
    try {
        BeanUtils.populate(user, parameterMap);
    } catch (IllegalAccessException | InvocationTargetException e) {
        e.printStackTrace();
    }
    System.out.println(user);
}
```

### 3.2 中文乱码问题

中文乱码仅针对请求体，因此对 get 方法没有效果。

对于 post 方法，需要添加一行：
```java
request.setCharacterEncoding("utf-8");
```

### 3.3 form 表单路径写法

三种写法：
1. 全路径。示例：`http://localhost:8080/app/submit`。

2. 相对路径：提交的地址相对于当前页面的相对路径。  
   示例：当前页面路径：`http://localhost:8080/app/form.html`，需要提交的路径 `http://localhost:8080/app/submit`。则相对路径就是 `submit`。

3. `/应用名/资源名` 路径写法。比如上例，可以写为 `/app/submit`。

推荐第三种路径写法。

### 3.4 转发和包含

一个 Servlet 对象无法获得另一个 Servelt 对象的引用；如果需要多个 Servet 组件共同协作（数据传递），只能使用 Servelt 规范为我们提供的两种方式：
- 请求转发：Servlet（源组件）先对客户请求做一些预处理操作，然后把请求转发给其他 Web 组件（目标组件）来完成包括生成响应结果在内的后续操作。

- 包含：Servelt（源组件）把其他 Web 组件（目标组件）生成的响应结果包含到自身的响应结果中。

转发和包含的共同点：
- 源组件和目标组件处理的都是同一个客户请求，源组件和目标组件共享同一个 ServeltRequest 和 ServletResponse 对象。

- 目标组件都可以为 Servlet、JSP 或 HTML 文档。

- 都依赖 javax.servlet.RequestDispatcher 接口。

Request 对象提供了一个 `getRequestDispatcher(String path)` 方法，该方法返回一个 requestDispatcher 对象，这个对象表示请求分发器，它有两个方法：
- `forward()`：把请求转发给目标组件。

- `include()`：包含目标组件的响应结果。

得到 RequestDispatcher 对象：
- ServletContext 对象的 `getRequestDispather(String path1)`。  
  path1 必须即以 `/` 开头，若用相对路径会抛出异常 IllegalArgumentException。

- ServletRequest 对象的 `getRequestDispatcher(String path2)`。  
  path2 可以用绝对路径也可以用相对路径

注：以 `/` 开头的路径写法，如果执行主体是服务器，那么就可以省略 `/`。

#### 3.4.1 转发

`forward(request, response)` 的处理流程：
1. 清空用于存放响应正文数据的缓冲区。

2. 如果目标组件为 Servlet 或 JSP，Tomcat 就调用它们，把它们产生的响应结果发送到客户端；  
   如果目标组件为文件系统中的静态 HTML 文档，Tomcat 就读取文档中的数据并把它发送给客户端。

特点：
1. 由于 `forward()` 方法先清空用于存放响应正文数据的缓冲区，因此源组件生成的响应结果（无论转发前后）不会被发送到客户端，只有目标组件生成的响应结果才会被送到客户端。

2. 如果源组件在进行请求转发之前，已经提交了响应结果（如调用了 response 的 flush 或 close 方法），那么 `forward()` 方法会抛出 IllegalStateException。为了避免该异常，不应该在源组件中提交响应结果。

#### 3.4.2 包含

`include(request, response)` 方法的处理流程：如果目标组件为 Servlet 或 JSP，就执行它们，并把它们产生的响应正文添加到源组件的响应结果中；如果目标组件为 HTML 文档，就直接把文档的内容添加到源组件的响应结果中。

特点：
1. 源组件与被包含的目标组件的输出数据都会被添加到响应结果中。

2. 在目标组件中对响应状态代码或者响应头所做的修改都会被忽略。


Request 对象同时也是一个域对象，开发人员通过 Request 对象在实现转发时，把数据通过 Request 对象带给其它 Web 资源处理。
- `setAttribute`。 

- `getAttribute`。  

- `removeAttribute`。

- `getAttributeNames`。

使用场景：
- 转发一般用在 Servlet 和页面之间。

- 包含一般用在页面和页面之间。

## 4 ServletResponse

HttpServletResponse 对象代表服务器的响应。这个对象中封装了向客户端发送数据、发送响应头，发送响应状态码的方法。

### 4.1 输出数据到客户端

`Response.getWriter().println()`。

### 4.2 中文乱码问题

第一步：设置服务器构建响应报文的字符。  
添加一行：
```java
response.setCharacterEncoding("utf-8");
```

第二步：设置客户端处理方式。
- 方式一：通过 meta 标签模拟请求头。  
  ```java
  out.write("<meta charset=utf-8' />".getBytes());
  ```

- 方式二：发送 content-type 相应头，告知浏览器使用哪种编码方式。  
  ```java
  response.setContentType("text/html;charset=UTF-8");
  ```

或者两步合一，直接设置：
```java
response.setHeader("Content-type", "text/html;charset=UTF-8");
````

### 4.3 输出字节数据

`getOutputStream()` 和 `getWriter()` 方法分别用于得到输出二进制数据、输出文本数据的 ServletOuputStream、Printwriter 对象。

`getOutputStream()` 和 `getWriter()` 这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法，否则会抛出异常。

Servlet 程序向 ServletOutputStream 或 PrintWriter 对象中写入的数据将被 Servlet 引擎从 response 里面获取，Servlet 引擎将这些数据当作响应消息的正文，然后再与响应状态行和各响应头组合后输出到客户端。 

Serlvet 的 service 方法结束后，Servlet 引擎将检查 getWriter 或 getOutputStream 方法返回的输出流对象是否已经调用过 close 方法，如果没有，Servlet 引擎将调用 close 方法关闭该输出流对象。 


示例：
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletOutputStream outputStream = response.getOutputStream();
    String path = getServletContext().getRealPath("1.jpg");
    FileInputStream fileInputStream = new FileInputStream(new File(path));
    int length = 0;
    byte[] bytes = new byte[1024];
    while ((length = fileInputStream.read(bytes)) != -1){
        outputStream.write(bytes, 0, length);
    }

    // request 提供的输入流以及 response 提供的输出流不关的话，tomcat 会在响应结束时给关闭
    fileInputStream.close();
}
```


### 4.4 刷新和重定向

#### 4.4.1 定时刷新

refresh 响应头设置定时刷新。  

方式一：每隔指定秒数刷新一下当前页面。  
  ```java
  response.getWriter().println(new Date());
  response.setHeader("refresh","2");
  ```

方式二：后面会带着一个 url，表示经过指定秒数之后，跳转至 url。
```java
response.setHeader("refresh","2;url=stream");

/*
/应用名/资源名路径写法：
response.setHeader("refresh","2;url=" + request.getContextPath() + "/stream");

也可以跳转至外部：
response.setHeader("refresh","2;url=http://www.baidu.com");
*/
}
```

#### 4.4.2 重定向

重定向机制的运作流程：
1. 用户在浏览器端输入特定 URL，请求访问服务器端的某个组件。

2. 服务器端的组件返回一个状态码为 302 的响应结果。

3. 当浏览器端接收到这种响应结果后，再立即自动请求访问另一个 Web 组件。

4. 浏览器端接收到来自另一个 Web 组件的响应结果。

HttpServeltResponse 的 `sendRedirect(String location)` 用于重定向。

示例：
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(302);
    response.setHeader("Location", request.getContextPath() + "/stream");
}
```
或者利用服务器提供的 api：
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.sendRedirect(request.getContextPath() + "/stream");
}
```

特点：
- Servlet 源组件生成的响应结果不会被发送到客户端。  
  `response.sendRedirect(String location)` 方法一律返回状态码为 302 的响应结果。

- 如果源组件在进行重定向之前，已经提交了响应结果，会抛出 IllegalStateException。为了避免异常，不应该在源组件中提交响应结果。  
  ```
  Cannot call sendRedirect() after the response has been committed
  ```

- 在 Servlet 源组件重定向语句后面的代码也会执行。

- 源组件和目标组件不共享同一个 ServletRequest 对象。

- 对于 `sendRedirect(String location)` 方法的参数，如果以 `/` 开头，表示相对于当前服务器根路径的 URL（不是当前应用的根目录）。以 `http://` 开头，表示一个完整路径。

- 目标组件不必是同一服务器上的同一个 Web 应用的组件，它可以是任意一个有效网页。


#### 4.4.3 转发、定时刷新、重定向区别联系

共同点：都可以用来进行页面跳转。

不同点：
- 转发只有一次请求；  
  刷新和重定向发起两次请求。

- 转发是 request 介导的；  
  刷新和重定向是 response 介导的。

- 转发可以共享 request 域；   
  刷新和重定向不可以。

- 转发只能应用内跳转；    
  刷新和重定向没有限制。

- 转发是服务器介导；  
  刷新和重定向是浏览器介导（`/` 开头路径写法不同）。

- 重定向状态码 302、307；  
  其他是 200。

### 4.5 下载

对于浏览器来说，浏览器可以打开的文件，默认会执行打开操作，对于无法打开的文件，默认会执行下载操作。  
但是，对于那些可以打开的文件，也可以设置一个响应头，直接让文件下载下来。  

示例：
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletOutputStream outputStream = response.getOutputStream();
    String path = getServletContext().getRealPath("1.jpg");
    FileInputStream fileInputStream = new FileInputStream(new File(path));
    int length = 0;
    byte[] bytes = new byte[1024];
    while ((length = fileInputStream.read(bytes)) != -1){
        outputStream.write(bytes, 0, length);
    }

    fileInputStream.close();

}
```

## 5 FileUpload

实现 Web 开发中的文件上传功能，需两步操作：
1. 在 Web 页面中添加上传输入项；

2. 在 Servlet 中读取上传文件的数据，并保存到服务器硬盘中。

### 5.1 Web 页面添加上传输入项

`<input type="file">` 标签用于在 Web 页面中添加文件上传输入项，设置文件上传输入项时须注意：
- 必须要设置 input 输入项的 name 属性，否则浏览器将不会发送上传文件的数据。

- 必须把 form 的 enctype 属值设为 `multipart/form-data`。设置该值后，浏览器在上传文件时，将把文件数据附带在 HTTP 请求消息体中，并使用 MIME 协议对上传的文件进行描述，以方便接收方对上传数据进行解析和处理。

- 表单的提交方式要是 `post`。

### 5.2 Servlet 读取上传文件

Request 对象提供了一个 `getInputStream` 方法，通过这个方法可以读取到客户端提交过来的数据。但由于用户可能会同时上传多个文件，在 Servlet 端编程直接读取上传数据，并分别解析出相应的文件数据是一项非常麻烦的工作。  

为方便用户处理文件上传数据，Apache 开源组织提供了一个用来处理表单文件上传的一个开源组件 `Commons-fileupload`，该组件性能优异，并且其 API 使用极其简单，可以让开发人员轻松实现 Web 文件上传功能。

使用 `Commons-fileupload` 组件实现文件上传，需要导入该组件相应的支撑 jar 包：`Commons-fileupload` 和 `commons-io`。  
`commons-io` 不属于文件上传组件的开发 jar 文件，但 `Commons-fileupload` 组件从 1.1 版本开始，它工作时需要 `commons-io` 包的支持。

### 5.3 文件存放位置

为保证服务器安全，上传文件应保存在应用程序的 `WEB-INF` 目录下，或者不受 Web 服务器管理的目录。  

为防止多用户上传相同文件名的文件，而导致文件覆盖的情况发生，文件上传程序应保证上传文件具有唯一文件名。  

为防止单个目录下文件过多，影响文件读写速度，处理上传文件的程序应根据可能的文件上传总量，选择合适的目录结构生成算法，将上传文件分散存储。

### 5.4 文件上传案例

实现步骤：
1. 创建 DiskFileItemFactory 对象，设置缓冲区大小和临时文件目录。

2. 使用 DiskFileItemFactory 对象创建 ServletFileUpload 对象，并设置上传文件的大小限制。

3. 调用 `ServletFileUpload.parseRequest` 方法解析 request 对象，得到一个保存了所有上传内容的 List 对象。

4. 对 list 进行迭代，每迭代一个 FileItem 对象，调用其 isFormField 方法判断是否是上传文件。
   - True 为普通表单字段，则调用 getFieldName、getString 方法得到字段名和字段值。
   
   - False 为上传文件，则调用 getInputStream 方法得到数据输入流，从而读取上传数据。

5. 编码实现文件上传。

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 避免中文乱码
    response.setContentType("text/html;charset=utf-8");

    // 判断是否存在上传文件
    boolean result = ServletFileUpload.isMultipartContent(request);
    if(!result){
        response.getWriter().println("不包含上传的文件");
        return;
    }

    // 创建 DiskFileItemFactory 对象
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletContext servletContext = getServletContext();
    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
    
    // 指定临时文件目录
    factory.setRepository(repository);  

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);
    
    // 避免上传的文件名中文乱码
    upload.setHeaderEncoding("utf-8");

    // 设置上传文件总量的最大值
    upload.setFileSizeMax(1024);

    try {
        // 解析 request 对象，并把表单中的每一个输入项包装成一个 fileItem 对象，并返回一个保存了所有FileItem 的 list 集合。 
        List<FileItem> items = upload.parseRequest(request);

        // 对 items 进行迭代
        Iterator<FileItem> iterator = items.iterator();
        while (iterator.hasNext()){
            // 每一个 item 其实就是一个 input 框的封装
            FileItem item = iterator.next();
            if(item.isFormField()){
                // 当前是普通的 form 表单数据
                processFormField(item);
            }else{
                // 当前是上传的文件
                processUploadedFile(item);
            }
        }
    } catch (FileUploadException e) {
        e.printStackTrace();
    }
}

/**
  * 处理上传的文件
  * @param item
  */
private void processUploadedFile(FileItem item) {
    String fieldName = item.getFieldName();
    String fileName = item.getName(); // 文件名
    System.out.println(fieldName + " === " + fileName);
    String realPath = getServletContext().getRealPath("upload/" + fileName);
    File file = new File(realPath);
    if(!file.getParentFile().exists()){
        file.getParentFile().mkdirs();
    }
    try {
        // 保存文件到硬盘中
        item.write(file);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
  * 处理普通的 form 表单数据
  * 只需要获取 name 和 value 值即可
  * 上传文件之后，不要再用 getParameter 去获取请求参数了
  * @param item
  */
private void processFormField(FileItem item) {
    String name = item.getFieldName();
    String value = null;
    try {
        // 避免中文乱码
        value = item.getString("utf-8");
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    System.out.println(name + " = " + value);
}
```

### 5.5 保存数据到 bean

以操作 form 表单为例：
```html
<form action="/upload3" enctype="multipart/form-data" method="post">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="file" name="image"><br>
    <input type="submit">
</form>
```

#### 5.5.1 中规中矩版

只需要在 `processUploadedFile` 方法和 `processFormField` 方法中添加 user 对象。  

```java
/**
  * 处理上传的文件
  * @param item
  * @param user
  */
private void processUploadedFile(FileItem item, User user) {
    String fieldName = item.getFieldName();
    String fileName = item.getName();
    System.out.println(fieldName + " === " + fileName);
    String UPLOAD_BASE = "upload/";
    UPLOAD_BASE = UPLOAD_BASE + fileName;
    String realPath = getServletContext().getRealPath(UPLOAD_BASE);
    File file = new File(realPath);
    if(!file.getParentFile().exists()){
        file.getParentFile().mkdirs();
    }
    try {
        // 保存文件到硬盘中
        item.write(file);

        // img src='/应用名/资源名'
        user.setImage(UPLOAD_BASE);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
  * 处理普通的 form 表单数据
  * 只需要获取 name 和 value 值即可
  * 上传文件之后，不要再用 getParameter 去获取请求参数了
  * @param item
  * @param user
  */
private void processFormField(FileItem item, User user) {
    String name = item.getFieldName();
    String value = null;
    try {
        value = item.getString("utf-8");
        if("username".equals(name)){
            user.setUsername(value);
        }else if("password".equals(name)){
            user.setPassword(value);
        }
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    System.out.println(name + " = " + value);
}
```

#### 5.5.2 优化版

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=utf-8");

    boolean result = ServletFileUpload.isMultipartContent(request);
    if(!result){
        response.getWriter().println("不包含上传的文件");
        return;
    }

    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletContext servletContext = getServletContext();
    File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
    factory.setRepository(repository);

    // Create a new file upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

    Map map = new HashMap();
    try {
        List<FileItem> items = upload.parseRequest(request);
        // 对 items 进行迭代
        Iterator<FileItem> iterator = items.iterator();
        while (iterator.hasNext()){
            //每一个 item 其实就是一个 input 框的封装
            FileItem item = iterator.next();
            if(item.isFormField()){
                // 当前是普通的 form 表单数据
                processFormField(item, map);
            }else{
                // 当前是上传的文件
                processUploadedFile(item, map);
            }
        }

    } catch (FileUploadException e) {
        e.printStackTrace();
    }
    //JDBC  user
    System.out.println(map);
    // 有一个 map，然后需要将数据封装到对象中
    User user = new User();
    try {
        BeanUtils.populate(user, map);
    } catch (IllegalAccessException e) {
        e.printStackTrace();
    } catch (InvocationTargetException e) {
        e.printStackTrace();
    }
    System.out.println(user);
}

/**
  * 处理上传的文件
  * @param item
  * @param map
  */
private void processUploadedFile(FileItem item, Map map) {
    String fieldName = item.getFieldName();
    String fileName = item.getName();
    // 将这个 fileName 变成唯一的一个
    fileName =  UUID.randomUUID().toString() + "-" + fileName;

    System.out.println(fieldName + " === " + fileName);
    String UPLOAD_BASE = "upload/";
    UPLOAD_BASE = UPLOAD_BASE + fileName;
    String realPath = getServletContext().getRealPath(UPLOAD_BASE);
    File file = new File(realPath);
    if(!file.getParentFile().exists()){
        file.getParentFile().mkdirs();
    }
    try {
        // 保存文件到硬盘中
        item.write(file);

        // img src='/应用名/资源名'
        map.put(fieldName, UPLOAD_BASE);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
  * 处理普通的 form 表单数据
  * 只需要获取 name 和 value 值即可
  * 上传文件之后，不要再用getParameter去获取请求参数了
  * @param item
  * @param map
  */
private void processFormField(FileItem item, Map map) {
    String name = item.getFieldName();
    String value = null;
    try {
        value = item.getString("utf-8");
        // 如果传入进来的是checkbox，那么下面需要变更一下
        map.put(name, value);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    System.out.println(name + " = " + value);
}
```

### 5.6 文件保存问题

#### 5.6.1 文件重名

添加时间戳：
```java
fileName =  UUID.randomUUID().toString() + "-" + fileName;
```

#### 5.6.2 单个目录文件数过多

利用 Hash 散列开。  

Filename 取哈希值，转化为 16 进制数字，生成多重文件夹（形如：`1/4 /a/ 5/ f/ 7/ 8/ f`）。

### 5.7 保存回显操作

保存：注册（上传头像），封装到 bean 中，将 bean 中的数据取出来，通过 JDBC 保存到数据库。

回显：到数据库里面去查询这条数据，然后封装到 bean 中，接下来，将它在页面显示出来。

注册成功之后，封装到 bean，不去保存到数据库，而是通过转发交给另外一个 servlet，该 servlet 在页面将之前选择的数据进行回显。

JDBC：
```java
// Class.forName(com.mysql.jdbc.Driver)
Connection conn = DriverManager.getConnection(url,user,password);
PreparedStatement psmt = Conn.preparedStatement();
ResultSet rs = Psmt.executeQuery();
While(rs.next){
    Rs.getString(columnName);
    Rs.getInt(columnName);
    New User;
    User.setxxx
}
```

## 6 Cookie

每个用户在使用浏览器与服务器进行会话的过程中，不可避免各自会产生一些数据，程序要想办法为每个用户保存这些数据。

### 6.1 Cookie 概述

Cookie 是客户端技术，服务器程序把每个用户的数据以 Cookie 的形式写给用户各自的浏览器。当用户使用浏览器再去访问服务器中的 Web 资源时，就会带着各自的数据去访问。这样，Web 服务器就能通过 Cookie 去识别用户了。Web 资源处理的就是用户各自的数据了。

Cookie 示例：  
<div align="center">
<img src="./img/p13.png">
</div>

javax.servlet.http.Cookie 类用于创建一个 Cookie，response 接口中定义了一个 `addCookie` 方法，它用于在其响应头中增加一个相应的 Set-Cookie 头字段。 同样，request 接口中也定义了一个 `getCookies` 方法，它用于获取客户端提交的 Cookie。  

Cookie 细节：
1. Cookie 的 name 和 value 值均是字符串类型；

2. Cookie 只能存储少量的数据，一般不超过 4k；

3. 不同浏览器之间不可以共享 Cookie。

Cookie 类常用方法： 
- `public Cookie(String name,String value)`

- `setValue`

- `getValue`

- `setMaxAge`

- `getMaxAge`

- `getName`

### 6.2 Cookie 案例

#### 6.2.1 案例 1

用两个浏览器模拟两个用户。直接在浏览器的窗口将登录的用户名打印出来。

login.html：
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form action="/app/login" method="post">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="submit">
</form>
</body>
</html>
```

Servlet1：
```java
@WebServlet("/login")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");
        Cookie cookie = new Cookie("username", username);
        response.addCookie(cookie);
        response.getWriter().println("登录成功，即将跳转至个人主页");
        response.setHeader("refresh","2;url=" + request.getContextPath() + "/info");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
```

Servlet2：
```java
@WebServlet("/info")
public class Servlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从请求头中拿到 cookie
        // 没有必要用 getHeader 去一点一点解析，因为 request 已经帮助封装
        response.setContentType("text/html;charset=utf-8");

        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("username".equals(cookie.getName())){
                    response.getWriter().println("欢迎您，" + cookie.getValue());
                }
            }
        }
    }
}
```

#### 6.2.2 案例 2

打印出上次访问该页面的时间。
```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for (Cookie cookie : cookies) {
            if("lastLogin".equals(cookie.getName())){
                String value = cookie.getValue();
                Date date = new Date(Long.parseLong(value));
                response.getWriter().println("last login: " + date);
            }
        }
    }
    Cookie cookie = new Cookie("lastLogin", System.currentTimeMillis() + "");
    response.addCookie(cookie);
}
```

注：Cookie 对象的 value 值中不能有空格。

### 6.3 Cookie 设置

#### 6.4.1 设置 Maxage

如果创建了一个 Cookie，并将它发送到浏览器，默认情况下它是一个会话级别的 Cookie（即存储在浏览器的内存中），用户退出浏览器之后即被删除。  
若希望浏览器将该 Cookie 存储在磁盘上，则需要使用 maxAge，并给出一个以秒为单位的时间。  
将最大时效设为 0 则是命令浏览器删除该 Cookie。

示例：
```java
// 保留两分钟
cookie.setMaxAge(60 * 2);
```

#### 6.4.2 设置 path

默认情况下，如果没有设置 path 的话，则当访问当前域名下任意资源时，均会带上该 Cookie，如果想仅部分路径携带 Cookie，则可以通过设置 path 来实现。

示例：
```java
@WebServlet("/path1")
public class Servlet5 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie cookie = new Cookie("name","path");
        cookie.setPath(request.getContextPath() + "/path2");
        response.addCookie(cookie);
    }
}
```

```java
@WebServlet("/path2")
public class Servlet6 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if("name".equals(cookie.getName())){
                    cookie.setPath(request.getContextPath() + "/path2");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }
}
```

path1 界面多次请求，Cookie 值也不会出现 name，访问 path2 界面会直接携带该 Cookie。
<div align="center">
<img src="./img/p14.png"></br>
<img src="./img/p15.png">
</div>


注：如果设置了 path，那么不能直接删除 Cookie，删除 Cookie 时，必须要和生成 Cookie 时设置相同的 path。

#### 6.4.3 设置 domain

浏览器有一个大的原则：不能设置和当前应用无关的域名 Cookie。比如当前域名是 localhost，然后设置了一个 Cookie，域名是 www.baidu.com，此时浏览器是不允许的。  

父子域名的规则：
- 父域名，如：gyh.com	127.0.0.1。

- 一级子域名，如：video.gyh.com	127.0.0.1。

- 二级子域名，如：story.video.gyh.com	127.0.0.1。

如果在父域名下，设置了一个 Cookie 的 domain 是 gyh.com，那么接下来，下面所有的子域名均可以共享这个 Cookie。


## 7 Session

### 7.1 Session 概述

保存会话数据的两种技术 Cookie 和 Session。
- Cookie 是客户端技术，程序把每个用户的数据以 Cookie 的形式写给用户各自的浏览器。当用户使用浏览器再去访问服务器中的 web 资源时，就会带着各自的数据去。这样，Web 资源处理的就是用户各自的数据了。

- Session 是服务器端技术，利用这个技术，服务器在运行时可以为每一个用户的浏览器创建一个其独享的 HttpSession 对象，由于 Session 为用户浏览器独享，所以用户在访问服务器的 Web 资源时，可以把各自的数据放在各自的 Session 中，当用户再去访问服务器中的其它 Web 资源时，其它 Web 资源再从用户各自的 Session 中取出数据为用户服务。

服务器给每个浏览器创建了一块区域，专门用来存放数据。只要是一个浏览器的行为，均可以把这些数据存放在这个 Session 中。即，浏览器和某个 Sessionn 对象做了一个绑定。

