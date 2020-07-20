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

### 1.3 入门案例

#### 1.3.1 案例 1

第一步：引入依赖、配置为 IDEA 中的标准 Web 应用。

SpringMVC：spring-web、spring-webmvc、servlet-api（provided）。

```xml
<packaging>war</packaging>

<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.2.6.RELEASE</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>3.0-alpha-1</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

第二步：配置 DispatcherServlet。

```xml
<!--注册 DispatcherServlet-->
<servlet>
    <servlet-name>dispatcherServlet</servlet-name>
    <!--dispatcherServlet 的全类名-->
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    
    <!-- 加载 xml 配置文件 -->
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:application.xml</param-value>
    </init-param>
</servlet>
<servlet-mapping>
    <servlet-name>dispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

第三步：注册组件、添加视图。

```java
@Component("/hello")    // 组件 id 和请求 url 建立映射关系 → 在 handleRequest 方法中处理该请求 url 对应的请求
public class HelloHandler implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        // 处理 model 数据，处理视图层的跳转
        ModelAndView modelAndView = new ModelAndView();
        // 相对于 webapp 的视图名
        modelAndView.setViewName("/WEB-INF/view/hello.jsp");
        // model 添加 key
        modelAndView.addObject("nowTime", new Date());
        return modelAndView;
    }
}
```

```xml
<context:component-scan base-package="com.gyh"/>
<!--HandlerMapping-->
<!--通过 handler 组件的 id 和请求 url 建立映射关系-->
<bean class="org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping"/>
<!--HandlerAdapter-->
<!--适配的是 SimpleController 类型的 Handler-->
<bean class="org.springframework.web.servlet.mvc.SimpleControllerHandlerAdapter"/>
<!--Handler → 组件-->
<!--通过 @Component 注解注册-->
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>hello</title>
</head>
<body>
    hello<br>
    ${nowTime}
</body>
</html>
```

第四步：配置 Tomcat。

第五步：运行。

<div align="center">
<img src="./img/p3.png">
</div>

#### 1.3.2 案例 2

第一步：同案例 1，搭建一个 SpringMVC 的 Web 应用。

第二步：引入 mvc 标签。

```xml
xmlns:mvc="http://www.springframework.org/schema/mvc"

xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd"
```

```xml
<context:component-scan base-package="com.gyh"/>
<mvc:annotation-driven/>
```

第三步：注册 controller 组件。

```java
@Controller
public class HelloHandler{

    // handler 方法
    @RequestMapping("hello")    // value 属性对应请求 url
    public ModelAndView hello() {

        ModelAndView modelAndView = new ModelAndView();
        // 相对于 webapp 的视图名
        modelAndView.setViewName("/WEB-INF/view/hello.jsp");
        // model 添加 key
        modelAndView.addObject("nowTime", new Date());
        return modelAndView;
    }
}
```

第四步：运行。

### 1.4 handlder 拓展

#### 1.4.1 @RequestMapping 注解

**（1）路径映射**  

通过 `@RequestMapping` 注解的 value 属性将 handler 方法和请求 url 建立映射关系。

**拓展 1**：可以将多个请求 url 映射到同一个 handler 方法上。  
示例：
```java
@RequestMapping(value = {"hello", "hello2", "hello3"})
```

**拓展 2**：可以使用 `*` 来通配。  
示例：
```java
@RequestMapping(value = {"hel*", "hello/*"}) 
```
则通过 `/hello` 或者 `/hello/123` 都能访问到。

**拓展 3**：窄化请求映射：类名上添加 `@RequestMapping` 注解，可以和方法上的注解结合使用。  
示例：
```java
@Controller
@RequestMapping("user")
public class HelloHandler{

    @RequestMapping("hello")
    public ModelAndView hello() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/view/hello.jsp");
        modelAndView.addObject("nowTime", new Date());
        return modelAndView;
    }

    @RequestMapping("hello2")
    public ModelAndView hello2() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/view/hello.jsp");
        modelAndView.addObject("nowTime", new Date());
        return modelAndView;
    }
}
```
更方便书写 `/user/hello` 和 `/user/hello1` 请求。


**（2）请求方法限定**  

在 `@RequestMapping` 注解可以增加 method 属性。

示例：
```java
@RequestMapping(value = "hello", method = RequestMethod.GET)

// 也可以添加多个 method 属性
@RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
```

引申的注解：
- `@GetMapping`：`@RequestMapping(method=GET)`

- `@PostMapping`：`@RequestMapping(method=POST)`


**（3）请求参数限定**  

在 `@RequestMapping` 的注解中增加 param 属性。  
示例：
```java
@RequestMapping(value = "hello", params = {"username", "pwd"})
```

注：多个请求参数之间的关系是 and。

**（4）请求 headler 限定**  

在 `@RequestMapping` 的注解中增加 headers 属性。  
示例：
```java
@RequestMapping(value = "hello", headers = {"hd=qwer"})
```

**（5）特殊的限定**  

produces 限定 Accept；consumes 限定 Content-Type。  
示例：
```java
@RequestMapping(value = "hello", produces = "application/json")
```
```java
@RequestMapping(value = "hello", consumes = "application/zip")
```

 #### 1.4.2 handler 的返回值

**（1）处理视图层**  

**情形 1**：返回值为 void。

使用 request 和 response。  

示例：
```java
// 不建议使用
@RequestMapping("void")
public void aVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("nowTime", new Date());
    request.getRequestDispatcher("/WEB-INF/view/void.jsp").forward(request,response);
}
```

**情形 2**：返回 ModelAndView

示例：
```java
@RequestMapping("modelAndView")
public ModelAndView modelAndView(){
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("/WEB-INF/view/modelAndView.jsp");
    return modelAndView;
}
```

**情形 3**：返回为 Sring。

物理视图名。  
示例：
```java
/**
* @param model
* @return 物理视图名
*/
// 返回值为字符串 String
@RequestMapping("physical/viewname")
public String physicalViewname(Model model){
    model.addAttribute("nowTime", new Date());
    return "/WEB-INF/view/string.jsp";  // 返回值为 viewName
}
```

逻辑视图名：配合组件 viewResolver，书写时省略前缀后缀。  
示例：
```xml
<!--最终的视图名 = 前缀 + 逻辑视图名 + 后缀-->
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <!--逻辑视图名的前缀-->
    <property name="prefix" value="/WEB-INF/view/"/>
    <!--逻辑视图名的后缀-->
    <property name="suffix" value=".jsp"/>
</bean>
```

```java
/**
* @param model
* @return 逻辑视图名
*/
@RequestMapping("logical/viewname")
public String logicalViewname(Model model){
    model.addAttribute("nowTime", new Date());
    return "string";    // 最终 viewName = 前缀 + 逻辑视图名 + 后缀
}
```
注：配置组件 viewResolver 后，modelAndView 中 viewName 、物理视图名的返回值也会拼接，即所有的返回值为字符串都会做一个拼接。

转发重定向。转发和重定向的是请求，而不是 jsp。  
示例：
```java
/**
* 用来接收转发和重定向
* @return
*/
@RequestMapping("hello")
public String hello(){
    return "hello";
}

/**
* @return
* forward：转发的 url，注意转发的 url 最前面加上 /
*/
@RequestMapping("forward")
public String forward(){
    return "forward:/return/view/hello";
}

/**
* @return
* redirect：重定向的 url，注意重定向的 url 最前面加上 /
*/
@RequestMapping("redirect")
public String redirect(){

    return "redirect:/return/view/hello";
}
```

**（2）不处理视图层（json）**  

首先需要添加依赖：
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.0</version>
</dependency>
```

给类添加注解 `@RestController` 或者给方法添加注解 `@ResponseBody`。

示例：
```java
//@Controller
@RestController
public class JsonController {

    @RequestMapping("user/query")
    //@ResponseBody
    public User queryUser(){
        User user = new User("zhang3", "123456");
        return user;
    }
}
```

#### 1.4.3 handler 形参

请求参数的封装。

**（1）使用 request 封装（不推荐）**  

使用 JavaEE 的封装形式：
```html
<body>
<h1>通过 request 获得请求参数</h1>
<form action="/parameter/request" method="post">
    用户：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit">
</form><hr>
</body>
```

```java
/**
 * handler 方法的请求参数封装
 */
@RestController
@RequestMapping("parameter")
public class ParameterController {

    @RequestMapping("request")
    public User request(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User(username, password);
        return user;
    }
}
```

**（3）自动封装**  

**方式 1**：直接写在 handler 方法的形参上。

要求：handler 方法的形参名和请求参数名一致（input 标签的 name 属性值）。

示例：
```html
<h1>直接在形参中封装请求参数</h1>
<form action="/parameter/base" method="post">
    用户：<input type="text" name="username"><br>
    密码：<input type="text" name="password"><br>
    <input type="submit">
</form><hr>
```

```java
@RequestMapping("base")
public User base(String username, String password){
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    return user;
}
```

**方式 2**：封装为 javabean。

要求：handler 方法的形参使用 javabean，这个 javabean 的成员变量名和请求参数名一致（input 标签的 name 属性值）。
- 可以为多级 javabean（嵌套 javabean）。

- 也可以为数组数据和 List。  
  数组：要求多个请求参数名一致。  
  List：通过下标进行分组，list 类型对应的成员变量名后。

```java
@RequestMapping("javabean")
public User javabean(User user){

    return user;
}
```

#### 1.4.4 post 请求乱码

web.xml 添加 filter：CharacterEncodingFilter。
```xml
<filter>
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <!-- 强制编码 -->
        <param-name>forceEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
    <init-param>
        <!-- 编码类型 -->
        <param-name>encoding</param-name>
        <param-value>utf-8</param-value>
    </init-param>
</filter>
<filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

