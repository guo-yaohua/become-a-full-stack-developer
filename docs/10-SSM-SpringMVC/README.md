# 十、SpringMVC

## 目录

- [十、SpringMVC](#十springmvc)
  - [目录](#目录)
  - [1 SpringMVC](#1-springmvc)
    - [1.1 SpringMVC 概述](#11-springmvc-概述)
    - [1.2 Web 应用的目录结构](#12-web-应用的目录结构)
      - [1.2.1 服务器下的目录结构](#121-服务器下的目录结构)
      - [1.2.2 IDEA 中的目录结构](#122-idea-中的目录结构)
    - [1.3 入门案例](#13-入门案例)
      - [1.3.1 案例 1](#131-案例-1)
      - [1.3.2 案例 2](#132-案例-2)
    - [1.4 @RequestMapping 注解](#14-requestmapping-注解)
      - [1.4.1 url 路径映射](#141-url-路径映射)
      - [1.4.2 请求方法限定](#142-请求方法限定)
      - [1.4.3 请求参数限定](#143-请求参数限定)
      - [1.4.4 请求 headler 限定](#144-请求-headler-限定)
    - [1.5 handler 的返回值](#15-handler-的返回值)
      - [1.5.1 处理视图层](#151-处理视图层)
      - [1.5.2 不处理视图层（json）](#152-不处理视图层json)
    - [1.6 handler 形参](#16-handler-形参)
      - [1.6.1 post 请求乱码](#161-post-请求乱码)
      - [1.6.2 使用 request 封装](#162-使用-request-封装)
      - [1.6.3 自动封装](#163-自动封装)
      - [1.6.4 SpringMVC 不包括的 converter。](#164-springmvc-不包括的-converter)
      - [1.6.5 File](#165-file)
      - [1.6.6 cookie 和 session](#166-cookie-和-session)
      - [1.6.7 json 数据作为形参](#167-json-数据作为形参)
    - [1.7 RESTful](#17-restful)
      - [1.7.1 @PathVaria](#171-pathvaria)
      - [1.7.2 @RequestParam](#172-requestparam)
      - [1.7.3 @RequestHeader](#173-requestheader)
      - [1.7.4 @CookieValue](#174-cookievalue)
      - [1.7.5 @SessionAttribute](#175-sessionattribute)
    - [1.8 静态资源处理](#18-静态资源处理)
      - [1.8.1 默认的 servlet](#181-默认的-servlet)
      - [1.8.2 默认的 servlet 对应的 handler](#182-默认的-servlet-对应的-handler)
      - [1.8.3 静态资源映射](#183-静态资源映射)
    - [1.8 异常处理](#18-异常处理)
      - [1.8.1 统一的异常处理器](#181-统一的异常处理器)
      - [1.8.2 针对异常 handler 处理](#182-针对异常-handler-处理)

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

**第一步**：引入依赖、配置为 IDEA 中的标准 Web 应用。

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

**第二步**：配置 DispatcherServlet。

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

**第三步**：注册组件、添加视图。

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

**第四步**：配置 Tomcat。

**第五步**：运行。

<div align="center">
<img src="./img/p3.png">
</div>

#### 1.3.2 案例 2

**第一步**：同案例 1，搭建一个 SpringMVC 的 Web 应用。

**第二步**：引入 mvc 标签。

```xml
xmlns:mvc="http://www.springframework.org/schema/mvc"

xsi:schemaLocation="http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd"
```

```xml
<context:component-scan base-package="com.gyh"/>
<mvc:annotation-driven/>
```

**第三步**：注册 controller 组件。

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

**第四步**：运行。


### 1.4 @RequestMapping 注解

#### 1.4.1 url 路径映射

通过 `@RequestMapping` 注解的 value 属性将 handler 方法和请求 url 建立映射关系。

**拓展 1**：可以将多个请求 url 映射到同一个 handler 方法上。  
示例：
```java
@RequestMapping(value = {"hello", "hello2", "hello3"})
```

**拓展 2**：url 可以使用 `*` 来通配。  
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
这样更方便书写 `/user/hello` 和 `/user/hello1` 请求。


#### 1.4.2 请求方法限定

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

通过限制请求方法，同一个 url 可以映射到不同的 handler 方法上。  
示例：
```java
@GetMapping("login")

@PostMapping("login")
```


#### 1.4.3 请求参数限定

在 `@RequestMapping` 的注解中增加 param 属性。  
示例：
```java
@RequestMapping(value = "hello", params = {"username", "pwd"})
```

注：多个请求参数之间的关系是 and。

#### 1.4.4 请求 headler 限定

在 `@RequestMapping` 的注解中增加 headers 属性。  
示例：
```java
@RequestMapping(value = "hello", headers = {"hd=qwer"})
```

两个特殊的限定：
- produces 限定 Accept。  

- consumes 限定 Content-Type。  

示例：
```java
@RequestMapping(value = "hello", produces = "application/json")
```
```java
@RequestMapping(value = "hello", consumes = "application/zip")
```

### 1.5 handler 的返回值

#### 1.5.1 处理视图层

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

**情形 2**：返回 ModelAndView。

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

物理视图名：Model 在形参中。  
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
* 状态码 302
*/
@RequestMapping("redirect")
public String redirect(){

    return "redirect:/return/view/hello";
}
```

#### 1.5.2 不处理视图层（json）

首先新增依赖：
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.0</version>
</dependency>
```

然后给类添加注解 `@RestController` 或者给方法添加注解 `@ResponseBody`。

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

### 1.6 handler 形参

请求参数的封装。

#### 1.6.1 post 请求乱码

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


#### 1.6.2 使用 request 封装

使用 JavaEE 的封装形式（不推荐）：
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

#### 1.6.3 自动封装

由 SpringMVC 提供格式转换。  

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
- 可以为多级 javabean（嵌套 javabean），使用 `.` 分开多级成员变量名。

- 也可以为数组数据和 List。  
  数组：要求多个请求参数名一致。  
  List：通过下标进行分组，list 类型对应的成员变量名后。

```jsp
<form action="/user/register" method="post">
    用户名：<input type="text" name="username"><br>
    密码：<input type="password" name="pwd"><br>
    生日：<select name="birthday.year">
            <option>1995</option>
            <option selected="selected">1996</option>
            <option>1997</option>
        </select>年
        <select name="birthday.month">
            <option>1</option>
            <option selected="selected">2</option>
            <option>3</option>
        </select>月
        <select name="birthday.day">
            <option>1</option>
            <option selected="selected">2</option>
            <option>3</option>
        </select>日<br>
    爱好 1：<input type="text" name="hobbies"><br>
    爱好 2：<input type="text" name="hobbies"><br>
    爱好 3：<input type="text" name="hobbies"><br>
    <input type="Submit">
</form><hr>
```

```java
@RequestMapping("javabean")
public User javabean(User user){

    return user;
}
```

#### 1.6.4 SpringMVC 不包括的 converter。

converter：请求参数的类型转换。

第一步：自定义 converter。

把不能自动封装的数据转换成形参中（或 javabean 成员变量）接收的数据类型。

示例：
```java
@Component
public class String2DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-mm-dd").parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
```

第二步：配置。

```java
<mvc:annotation-driven conversion-service="conversionService"/>

<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
    <property name="converters">
        <set>
            <ref bean="string2DateConverter"/>
        </set>
    </property>
</bean>
```

第三步：使用。

```java
@RestController
public class ParameterController {

    @RequestMapping("date")
    public String date(Date birthday) {
        return birthday.toString();
    }
}
```

#### 1.6.5 File

File 也可以直接在形参中接收。

第一步：导包。

```xml
<dependency>
    <groupId>commons-fileupload</groupId>
    <artifactId>commons-fileupload</artifactId>
    <version>1.4</version>
</dependency>
```

第二步：注册组件。

```xml
<!--id 为指定值，不能修改为其他值-->
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <property name="maxUploadSize" value="500000"/>
</bean>
```

第三步：使用。

```jsp
<h1>单文件上传</h1>
<form action="/file/upload" enctype="multipart/form-data" method="post">
    <input type="file" name="myfile"><br>
    <input type="submit">
</form><hr>

<h1>多文件上传</h1>
<form action="/files/upload" enctype="multipart/form-data" method="post">
    <input type="file" multiple name="myfiles"><br>
    <input type="file" multiple name="myfiles"><br>
    <input type="submit">
</form>
```

```java
// 单文件上传
@RequestMapping("file/upload")
public String fileUpload(MultipartFile myfile){

    String originalFilename = myfile.getOriginalFilename();
    File file = new File("D:\\spring\\upload", originalFilename);
    try {
        myfile.transferTo(file);
    } catch (IOException e) {
        e.printStackTrace();
    }
    return "ok";
}

// 多文件上传
@RequestMapping("files/upload")
public String filesUpload(MultipartFile[] myfiles){
    String parent = "D:\\spring\\upload";
    for (MultipartFile myfile : myfiles) {
        String originalFilename = myfile.getOriginalFilename();
        // 文件名有可能发生重复，可以利用 uuid 改写上传后的文件名
        String s = UUID.randomUUID().toString();
        File file = new File(parent, originalFilename);
        try {
            myfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return "ok";
}
```

#### 1.6.6 cookie 和 session

**（1）通过 request 获得**  

```java
@RequestMapping("get/cookie")
public String getCookie(HttpServletRequest request){
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        String name = cookie.getName();
        String value = cookie.getValue();
        System.out.println("cookie 的 key 为" + name + ", value 为" + value);
    }
    return "ok";
}


@RequestMapping("put/session")
public String putSession(HttpServletRequest request,String username){
    HttpSession session = request.getSession();
    session.setAttribute("username", username);
    System.out.println("putSession 方法向 session 放入 key 为 username 所对应的值为：" + username);
    return "ok";
}


@RequestMapping("get/session")
public String getSession(HttpServletRequest request){
    HttpSession session = request.getSession();
    Object username = session.getAttribute("username");
    System.out.println("getSession 方法取出 username 对应的值为：" + username);
    return "ok";
}
```

**（2）HttpSession 写到形参**

```java
// 直接在形参中获得 session
@RequestMapping("get/session")
public String getSession(HttpSession session){

    Object username = session.getAttribute("username");
    System.out.println("getSession 方法取出 username 对应的值为：" + username);
    return "ok";
}
```

#### 1.6.7 json 数据作为形参

首先需要新增依赖：
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.0</version>
</dependency>
```

然后通过 `@RequestBody` 注解即可使用。  
示例：
```java
@PostMapping("login")
public BaseRespVo login(@RequestBody User user){

    BaseRespVo baseRespVo = new BaseRespVo();
    baseRespVo.setData(user);
    baseRespVo.setMsg("注册成功");
    baseRespVo.setErrno(0);
    return baseRespVo;
}
```

 `@RequestBody` 注解除了以 javabean 来接收 json 数据，还可以以通过 map 来接收。

```java
@RequestMapping("login")
public BaseRespVo login(@RequestBody Map map){

    BaseRespVo baseRespVo = new BaseRespVo();
    baseRespVo.setData(map);
    baseRespVo.setMsg("注册成功");
    baseRespVo.setErrno(0);
    return baseRespVo;
}
```


### 1.7 RESTful


RESTful（Representational State Transfer）：表现层状态转换。

#### 1.7.1 @PathVaria

拿到请求 url 中的一部分作为请求参数（最常用）。

示例：
```java
@RequestMapping("{userValue}/article/detail/{articleIdValue}")
public BaseRespVo queryArticleDetail(@PathVariable("userValue") String user,
                                        @PathVariable("articleIdValue") Integer articleId){
    Article article = new Article();
    article.setUser(user);
    article.setArticleId(articleId);
    //BaseRespVo<Article> articleBaseRespVo = new BaseRespVo<>();
    //articleBaseRespVo.setData(article);
    //articleBaseRespVo.setErrno(0);
    //articleBaseRespVo.setMsg("成功查询文章信息");
    //return articleBaseRespVo;
    return BaseRespVo.ok(article, "成功查询文章信息");
}
```


#### 1.7.2 @RequestParam

拿到请求参数作为 handler 方法的形参。  
示例：
```java
@RequestMapping(value = "login")
public BaseRespVo login(@RequestParam("username") String usernameValue,
                        @RequestParam("password") String passwordValue){
    User user = new User();
    user.setUsername(usernameValue);
    user.setPassword(passwordValue);
    return BaseRespVo.ok(user);
}
```

注：请求参数名之前是和 handler 方法的形参名对应的，增加对应的 `@RequestParam` ，该注解中的 value 要和请求参数名对应。即增加注解后，形参名可以修改。


#### 1.7.3 @RequestHeader

拿到请求头中的值作为请求参数。  
示例：
```java
@RequestMapping("fetch/header")
public BaseRespVo fetchHeader(@RequestHeader("Accept") String[] acceptArray,
                                @RequestHeader("Accept-Encoding") String acceptEncoding,
                                @RequestHeader("Accept-Language") String acceptLanguage){

    return BaseRespVo.ok();
}
```

注：通过 `@RequestHeader` 注解指定请求头的 key，在形参中增加注解。handler 方法的形参可以接收 String，也可以接收 String 数组（通过逗号分隔为数组）。

#### 1.7.4 @CookieValue

示例：
```java
@RequestMapping("fetch/cookie/value")
public BaseRespVo fetchCookieValue(@CookieValue("zhang3") String value){

    return BaseRespVo.ok();
}
```


#### 1.7.5 @SessionAttribute

示例：
```java
@RequestMapping("fetch/session/value")
public BaseRespVo fetchSessionValue(@SessionAttribute("username") String username){

    return BaseRespVo.ok();
}
```

### 1.8 静态资源处理

#### 1.8.1 默认的 servlet

配置 web.xml：
```xml
<!--<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.jpg</url-pattern>
</servlet-mapping>
<servlet-mapping>
    <servlet-name>default</servlet-name>
    <url-pattern>*.png</url-pattern>
</servlet-mapping>-->
```

#### 1.8.2 默认的 servlet 对应的 handler

```xml
<mvc:default-servlet-handler/>
```

#### 1.8.3 静态资源映射

请求 url 和静态资源所处的路径建立映射映射，通过请求 url 可以访问到对应路径的静态资源。

静态资源映射路径：
- classpath（java\ 和 resources → 打包后 war 里 WEB-INF/classes）

- web 根路径（webapp → 打包后的根目录）

- 纯文件系统的映射。

访问静态资源所需要的 url：mapping 中的值 + 静态资源相对于 location 的值。
- mapping 是和请求 url 相关的，`**` 就是通配多级 url。

- location 文件真实存在的路径：classpath、web 根路径、file文件路径。

示例：
```xml
<!--classpath 路径-->
<mvc:resources mapping="/pic1/**" location="classpath:/"/>

<!--web 根路径-->
<mvc:resources mapping="/pic2/**" location="/"/>

<mvc:resources mapping="/pic3/**" location="/WEB-INF/pic/"/>

<!--文件路径-->
<mvc:resources mapping="/pic4/**" location="file:D:/spring/pic/"/>
```

### 1.8 异常处理

#### 1.8.1 统一的异常处理器

发生了异常都进入到该处理器中处理，针对 ModelAndView。

示例：
```java
/**
 * 当 handler 方法发生异常，异常向上抛出，通过该统一的异常处理器处理
 * 只需要实现接口，然后注册到容器中就可以了
 */
//@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object handler, Exception exception) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/WEB-INF/view/exception.jsp");
        if (exception instanceof CustomException) {
            modelAndView.addObject("message", "自定义异常");
        } else {
            String message = exception.getMessage();
            modelAndView.addObject("message", message);
        }
        return modelAndView;
    }
}
```

```java
@RequestMapping("view")
public String view(int id) throws Exception {
    if (id == 1) {
        throw new Exception("id 不能为 1");
    } else if (id == 2){
        throw new CustomException();
    }
    return "/WEB-INF/view/view.jsp";
}
```


#### 1.8.2 针对异常 handler 处理

通过 handler 处理指定异常， ModelAndView 和 json 都能处理。

示例：
```java
@ControllerAdvice
// 如果所有的 handler 处理的都是 json 数据，那么可以在类上增加 @ResponseBody
public class ExceptionControllerAdvice {

    // 处理 ModelAndView
    @ExceptionHandler(CustomException.class)
    public String handleException(){
        return "/WEB-INF/view/view.jsp";
    }

    // 处理 json
    @ResponseBody
    @ExceptionHandler(ParameterException.class)
    public BaseRespVo handleException2(ParameterException exception){
        return BaseRespVo.fail();
    }

    // 处理 json
    @ResponseBody
    @ExceptionHandler(value = {CustomException2.class, CustomException3.class})
    public BaseRespVo handleManyException(Exception exception){
        return BaseRespVo.fail();
    }
}
```

```java
@RequestMapping("json")
@ResponseBody
public BaseRespVo json(int id) throws Exception {
    if (id == 1) {
        throw new CustomException();
    } else if (id == 2) {
        throw new ParameterException();
    }
    return BaseRespVo.ok();
}
```