# 十二、SpringBoot

## 目录

- [十二、SpringBoot](#十二springboot)
  - [目录](#目录)
  - [1 SpringBoot](#1-springboot)
    - [1.1 创建 SpringBoot](#11-创建-springboot)
      - [1.1.1 网页端创建](#111-网页端创建)
      - [1.1.2 IDEA 创建](#112-idea-创建)
      - [1.1.3 改造 Maven 项目](#113-改造-maven-项目)
    - [1.2 启动类](#12-启动类)
    - [1.3 pom 文件](#13-pom-文件)
    - [1.4 application 配置文件](#14-application-配置文件)
      - [1.4.1 配置文件形式](#141-配置文件形式)
      - [1.4.2 属性注入](#142-属性注入)
      - [1.4.4 多个配置文件](#144-多个配置文件)
  - [2 Spring Boot 整合视图技术](#2-spring-boot-整合视图技术)
  - [3 Spring Boot 整合 Web](#3-spring-boot-整合-web)
      - [1.4.1 搭建 SpringBoot Web 项目](#141-搭建-springboot-web-项目)
      - [1.4.2 静态资源](#142-静态资源)
      - [1.4.3 文件上传组件](#143-文件上传组件)
      - [1.4.4 Spring MVC 配置类](#144-spring-mvc-配置类)
      - [1.4.5 Converter](#145-converter)
    - [1.5 整合 MyBatis](#15-整合-mybatis)
      - [1.5.1 配置 datasource](#151-配置-datasource)
      - [1.5.2 mapper](#152-mapper)
  - [2 Shiro 权限管理](#2-shiro-权限管理)
    - [2.1 权限管理](#21-权限管理)
      - [2.1 用户认证](#21-用户认证)
      - [2.2 用户授权](#22-用户授权)
      - [2.3 权限管理模型](#23-权限管理模型)
      - [2.4 建立在通用模型下的权限管理](#24-建立在通用模型下的权限管理)
    - [2.2 Shiro](#22-shiro)
      - [2.2.1 Shiro 概述](#221-shiro-概述)
      - [2.2.2 入门案例 1](#222-入门案例-1)
      - [2.2.3 入门案例 2](#223-入门案例-2)
      - [2.2.4 SpringBoot 中使用 Shiro](#224-springboot-中使用-shiro)
  - [3 项目常用](#3-项目常用)
    - [3.1 跨域](#31-跨域)
    - [3.2 分页](#32-分页)
    - [3.3 日期格式限定](#33-日期格式限定)

## 1 SpringBoot 

使用 SSM 开发项目的不足之处：
- 创建 SSM 项目，比较麻烦。

- 配置比较麻烦。

- 依赖的配置比较多，不方便。

- 集成第三方的组件、框架比较麻烦的。


SpringBoot 优点：
- 构建项目非常方便。

- 配置简单，且约定大于配置。

- 无缝集成主流第三方框架。

- SpringBoot 不需要额外配置 JavaEE 容器。可以通过 jar 包的形式来运行。


### 1.1 创建 SpringBoot

#### 1.1.1 网页端创建

访问 `start.spring.io` 创建应用。

<div align="center">
<img src="./img/p1.png">
</div>

#### 1.1.2 IDEA 创建

new project：
<div align="center">
<img src="./img/p2.png">
</div>

基础设置：
<div align="center">
<img src="./img/p3.png">
</div>

选择依赖和 Spring Boot 版本：
<div align="center">
<img src="./img/p4.png">
</div>

项目设置：
<div align="center">
<img src="./img/p5.png">
</div>

#### 1.1.3 改造 Maven 项目

**第一步**：修改 pom.xml 文件。

添加 `<parent>` 标签：
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.6.RELEASE</version>
</parent>
```

添加依赖：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

**第二步**：添加启动类。

```java
@RestController
@EnableAutoConfiguration
public class App {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
```

### 1.2 启动类

```java
package com.gyh.demoTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTestApplication.class, args);
    }
}
```

`@SpringBootApplication` 注解默认设置扫描包的范围为启动类路径。

main 方法中可以添加各种配置，例如禁用 Banner：
```java
public static void main(String[] args) {

    SpringApplicationBuilder builder = new SpringApplicationBuilder(DemoTestApplication.class);
    SpringApplication build = builder.build();
    build.setBannerMode(Banner.Mode.OFF);
    build.run(args);
}
```

### 1.3 pom 文件

任一方式创建 Spring Boot 项目后，在 pom.xml 文件中都存在 `<parent>` 标签：
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.3.2.RELEASE</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```

`<parent>` 标签的基本功能：
- 定义了 Java 编译版本为 1.8 。

- 使用 UTF-8 格式编码。

- 继承自 spring-boot-dependencies，这个里边定义了依赖的版本，也正是因为继承了这个依赖，所以我们在写依赖时才不需要写版本号。

- 执行打包操作的配置。

- 自动化的资源过滤。

- 自动化的插件配置。

- 针对 `application.properties` 和 `application.yml` 的资源过滤，包括通过 profile 定义的不同环境的配置文件，例如 `application-dev.properties` 和 `application-dev.yml`。

在 `xx\.m2\repository\org\springframework\boot\spring-boot-starter-parent\2.4.0.RELEASE\spring-boot-starter-parent-2.4.0.RELEASE.pom` 可以看到它这些功能的实现：
```xml
<modelVersion>4.0.0</modelVersion>
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-dependencies</artifactId>
  <version>2.4.0</version>
</parent>
<artifactId>spring-boot-starter-parent</artifactId>
<packaging>pom</packaging>
<name>spring-boot-starter-parent</name>
<description>Parent pom providing dependency and plugin management for applications built with Maven</description>
<properties>
  <java.version>1.8</java.version>
  <resource.delimiter>@</resource.delimiter>
  <maven.compiler.source>${java.version}</maven.compiler.source>
  <maven.compiler.target>${java.version}</maven.compiler.target>
  <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
</properties>

...
```

再查看其 `<parent>` 指向的的 `spring-boot-dependencies` 文件，可以发现其 `<properties>` 文件中存在大量的版本定义，这即是 Spring Boot 项目中部分依赖不需要写版本号的原因。

```xml
<properties>
    <activemq.version>5.16.0</activemq.version>
    <antlr2.version>2.7.7</antlr2.version>
    <appengine-sdk.version>1.9.83</appengine-sdk.version>

    ...
</properties>
```

实际开发中，公司可能会要求 Spring Boot 项目继承自公司内部的 parent ，此时可以自行定义 dependencyManagement 节点来解决依赖的版本号问题。  
示例：
```xml
<dependencyManagement>
    <dependencies>    
        <dependency>         
            <groupId>org.springframework.boot</groupId>   
            <artifactId>spring-boot-dependencies</artifactId>
            <version>2.4.0.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```


### 1.4 application 配置文件

#### 1.4.1 配置文件形式

Spring Boot 支持 properties 配置 和 yaml 配置。

properties 配置：无序，后缀 `.properties`，`key=value` 形式。  
示例：
```properties
server.port=8081
server.servlet.context-path=/demo1
```

yaml 配置：有序，后缀 `.yml / .yaml`，`key:value` 形式。    
示例：
```yml
server:
  port: 8081
  servlet:
    context-path: /demo1
```

注：
- 下一级和上一级需要空格，几个空格都行，但是同一级需要对齐。
- `:` 后需要添加一个空格。



#### 1.4.2 属性注入

配置文件可以给容器中组件的成员变量赋值。  
示例：
```yml
file:
  baseName: zhang3
  maxSize: 100
```

```java
@Component
@Data
public class FileComponent {
    //使用 @value 注解可以引用 SpringBoot 配置文件中的 key
    @Value("${file.baseName}")
    String baseName;
    @Value("${file.maxSize}")
    int maxSize;
}
```

配置文件中可以引入额外的配置文件：
- `@PropertySource` 注解：只支持 properties 类型。  
  示例：
  ```java
  @PropertySource(value = "classpath:file.properties")
  ```

- `@ImportResource` 注解。  
  示例：
  ```java
  @ImportResource(locations = "classpath:bean.xml")
  ```

  bean.xml：
  ```xml
  <bean class="com.gyh.component.SpringComponent"/>
  ```

以上属性注入方式 Spring 就已经提供，Spring Boot 提供了更方便的形式：`@ConfigurationProperties` 注解。  
示例：
```java
@Component
@Data
@ConfigurationProperties(prefix = "file")
public class FileComponent {

    String baseName;

    int maxSize;
}
```

通过 spring-boot-configuration-processor 依赖，可以在写配置文件时给与提示。  
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
> 引进依赖后可能会没有提示，重新 run 一下即可。

配置文件面对复杂的类型，也支持不同的书写方式。  
示例：
```java
@Component
@Data
@ConfigurationProperties(prefix = "file")
public class FileComponent {

    String baseName;
    int maxSize;
    boolean saved;
    List list1;
    List list2;
    Map map1;
    Map map2;
    FileDetail fileDetail;
}
```

`.properties` 形式：
```properties
file.baseName=zhang3
file.maxSize=120
file.saved=true

# list 两种写法
file.list1=data1, data2, data3

file.list2[0]=data1
file.list2[1]=data2
file.list2[2]=data3

# map 两种写法
file.map1.key1=value1
file.map1.key2=value2

file.map2[key1]=value1
file.map2[key2]=value2

# javabean
file.file-detail.name=li4
file.file-detail.size=1000
```

`.yml` 形式：
```yml
file:
  base-name: zhang3
  max-size: 123
  saved: true

  # list 两种写法
  list1: data1, data2, data3
  list2:
    - data1
    - data2
    - data3

  # map 两种写法
  map1:
    key1: value1
    key2: value2
  map2: {key1: value1, key2: value2}

  # javabean 两种写法
  fileDetail:
    name: li4
    size: 2000
  # file-detail:{name: li4, size: 2000}
```

配置文件支持随机数占位符，如：
- `${random.value}`
- `${random.int}`
- `${random.long}`
- `${random.int(10)}`
- `${random.(1, 10)}`

```yml
file:
  base-name: zhang3
  max-size: ${random.int}
```

注：随机数在应用启动的时候，就已经生成，在程序运行过程中不会发生改变。

支持属性占位符，如：
```yml
file:
  file-path: d://spring/boot/
  pic-path: ${file.file-path}pic/
  xml-path: ${file.file-path}xml/
```


#### 1.4.4 多个配置文件

实际开发中会配置多个配置文件：
- 主配置文件：
  - application.properties
  - application.yml

- 分配置文件：
  - application-*.properties
  - application-*.yml


相同的 key 在不同的环境下有不同的值，把这些不同的值提取出来，分别放到不同的分配置文件中，然后通过主配置文件选择性地激活分配置文件。把不同类型的配置放入到不同的配置文件，完成解耦。

示例：
```yml
# applicaton.yml
spring:
  profiles:
    active: cigma, other
```

```yml
# application-alpha.yml
server:
  port: 8081
```

```yml
# application-beta.yml
server:
  port: 8082
```

```yml
# application-cigma.yml
server:
  port: 8083
```

也可以通过同一个配置文件中创建多个子配置文件实现，使用 `---` 的方式来分割。  
示例：
```yml
spring:
  profiles:
    active: cigma

---
spring:
  profiles: alpha
server:
  port: 8081

---
spring:
  profiles: beta
server:
  port: 8082

---
spring:
  profiles: cigma
server:
  port: 8083
```

## 2 Spring Boot 整合视图技术

## 3 Spring Boot 整合 Web

#### 1.4.1 搭建 SpringBoot Web 项目

**第一步**：引入依赖。

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

通常 SpringBoot 支持某个框架的依赖都叫 spring-boot-starter-xxx。这个依赖会提供这个框架所必须的依赖，注册一些默认的组件。  
如 spring-boot-starter-web：
<div align="center">
<img src="./img/p6.png">
</div>

**第二步**：Controller。

```java
@RestController
public class HelloController {

    @RequestMapping("hello")
    public BaseRespVO hello(){
        return BaseRespVO.ok("hello SpringBoot");
    }
}
```

**第三步**：启动项目。

<div align="center">
<img src="./img/p7.png">
</div>


spring-boot-starter-web 自带 Tomcat，可以在配置文件中定义 Tomcat 部分属性。
示例：
```yml
server:
  # 修改服务器端口号
  port: 8081
  servlet:
    # 修改上下文路径
    context-path: /test
  tomcat:
    # 配置 Tomcat URL 编码
    uri-encoding: utf-8
```

也可以不使用 Tomcat，使用别的服务。比如使用 Jetty：
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jetty</artifactId>
</dependency>
```

<div align="center">
<img src="./img/p16.png">
</div>

Spring Boot 可以直接打包为 jar 包启动：

**第一步**：打包。

<div align="center">
<img src="./img/p8.png">
</div>

**第二步**：运行。

<div align="center">
<img src="./img/p9.png">
</div>

#### 1.4.2 静态资源

静态资源默认路径：
- `classpath:\/META-INF\/resources\/`
- `classpath:\/resources\/`
- `classpath:\/static\/`
- `classpath:\/public\/`

可以添加配置：
```yml
spring:
  resources:
    static-locations: file:D:\spring\pic\ #location
  mvc:
    static-path-pattern: /pic1/** #mapping
```

#### 1.4.3 文件上传组件

multipartResolver 会自动注册。

#### 1.4.4 Spring MVC 配置类

`@EnableWebMvc`：全面接管 MVC 的配置，配置文件中关于 Web 的配置由这个配置类接管。

`@Configuration`：该配置类和 SpringBoot 配置文件中的配置是一个合作关系。

#### 1.4.5 Converter

在 Converter 类上增加 `@Component` 注解，注册到容器中就生效了。

### 1.5 整合 MyBatis

mybatis-spring-boot-starter

```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.1.2</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
```

#### 1.5.1 配置 datasource

```yml
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/test_db
    username: root
    password: 123456
```

#### 1.5.2 mapper

启动类上添加注解：
```java
@MapperScan(basePackages = "com.gyh.mapper")
```


## 2 Shiro 权限管理

### 2.1 权限管理

权限管理要实现对用户访问系统的控制，按照安全规则或者安全策略控制用户可以访问而且只能访问自己被授权的资源。
只要有用户参与的系统一般都要有权限管理。

权限管理包括用户认证和授权两部分。

#### 2.1 用户认证

常用的用户身份验证的方法：用户名密码方式、指纹、声纹。

<div align="center">
<img src="./img/p10.png">
</div>

关键对象：
- subject：原意是主题，主体，可以理解为用户。  
  当用户要去访问系统的资源，系统需要对 subject 进行身份认证。

- principal：原意是当事人。可以理解为用户身份信息。  
  通常是唯一的，一个主体还有多个身份信息，但是都有一个主身份信息（primary principal）。

- credential：凭证信息。可以是密码 、证书、指纹，声纹。

总结：主体在进行身份认证时需要提供身份信息或凭证信息。

#### 2.2 用户授权

用户授权，简单理解为访问控制，在用户认证通过后，系统对用户访问资源进行控制，用户只能访问那些具有资源的访问权限的资源。

<div align="center">
<img src="./img/p11.png">
</div>

授权的过程理解为：who 对 what（which）进行 how 操作。

关键对象：
- who：principal 主体（即 subject）在认证通过后系统进行访问控制。

- what（which）：资源（Resource），subject 必须具备资源的访问权限才可访问该资源。  
  资源分为资源类型和资源实例：
  - 系统的用户信息就是资源类型，相当于 Java 类。

  - 系统中 id 为 001 的用户就是资源实例，相当于 new 的 Java 对象。
  
- how：什么操作。用户添加、用户修改、商品删除。这些东西可以表示为具体的权限。  
  可以进行用户删除操作，就是具备用户删除的权限。


总结：权限 / 许可（permission），又称针对资源的权限或许可，subject 具有 permission 访问资源，如何访问 / 操作需要定义 permission。


#### 2.3 权限管理模型

**（1）基本模型**  

一套通用的权限管理模型
- 用户身份信息（用户名和密码）。

- 资源（资源的名称，主键 ）。

- 权限（这是一个 how）（权限的名称，主键）。

三者之间的关系是：
<div align="center">
<img src="./img/p12.png">
</div>

用户对某个资源的具体权限，这种 case 表示起来不太方便。  

通常企业开发中将资源和权限表合并为一张权限表，如：资源（资源名称、访问地址）、权限（权限名称、资源id）合并为：权限（权限名称、资源名称、资源访问地址）。  
比如用户查询：user:query，user，/user/query.action。  

这样模型可以简化为：
<div align="center">
<img src="./img/p13.png">
</div>

**（2）通用模型（role角色）**  

基本模型的不足之处：需要给每个具体的用户增加其对应的权限。如果用户很多，有 100 个，那么很麻烦。所以我们可以给用户分类。   
比如：  
- 有 90 个都是普通用户，具备的权限是一样的。
- 有 9 个都是管理员，具备的权限都是一样的。
- 有 1 个是超级管理员，具备的权限是一样的。

所以把模型优化成如下的 case：  
<div align="center">
<img src="./img/p14.png">
</div>

上图常被称为权限管理的通用模型，不过企业在开发中根据系统自身的特点还会对上图进行修改，但是用户、角色、权限、用户角色关系、角色权限关系是需要去理解的。

通常给用户分配资源权限需要将权限信息持久化，比如存储在关系数据库中。  
把用户信息、权限管理、用户分配的权限信息写到数据库（权限数据模型）。

#### 2.4 建立在通用模型下的权限管理

用户首先需要认证，认证之后访问就可以获取用户的角色，从而获取到用户具备的权限。这个流程成为授权。

当用户访问某个资源或者对某个资源进行操作时，需要分配相应的权限才可以访问或操作相应的资源。权限是对于资源的操作许可。

根据授权的不同依据，可以分为两种：基于角色的授权和基于资源的授权。

**（1）基于角色的授权**  

基于角色的访问控制（role  based  access  control）。  

比如系统角色包括：部门经理和总经理。  
当用户进行某个操作的时候，需要判断用户的角色。比如总经理可以查看所有员工的基本信息，部门经理只能查询自己部门的员工基本信息。

优缺点

问题：
如需要变更为部门经理和总经理都可以进行用户基本信息查看：
```java
// 如果该 user 是总经理则可以访问 if 中的代码
if(user.hasRole('总经理')){
	// 系统资源内容
	// 用户报表查看
}
```
变更后
```java
if(user.hasRole('部门经理') || user.hasRole('总经理')){
	// 系统资源内容
	// 用户报表查看
}
```

角色针对人划分的，人作为用户在系统中属于活动内容，如果该角色可以访问的资源出现变更，需要修改代码，所以基于角色的访问控制是不利于系统维护（可扩展性不强）。

**（2）基于资源的授权**

基于资源的访问控制（Resource  based  access  control）。

资源在系统中是不变的，比如资源有：类中的方法、页面中的按钮。  
如果某个角色对某个资源的访问权限发生变化了。
比如刚刚的 case：
```java
if(user.hasPermission ('用户报表查看（权限标识符）')){
	// 系统资源内容
	// 用户报表查看
}
```

变更后，数据库里增加该用户的权限即可，代码无需修改。  
所以通常建议使用基于资源的访问控制实现权限管理。

### 2.2 Shiro

#### 2.2.1 Shiro 概述

Shiro 是 apache 的一个开源框架，是一个权限管理的框架，实现用户认证、用户授权。

Spring 中有 spring security（原名 Acegi），是一个权限框架。它和 Spring 依赖过于紧密，没有 Shiro 使用简单。  

Shiro 不依赖于 Spring，Shiro 不仅可以实现 Web 应用的权限管理，还可以实现 c/s 系统、分布式系统权限管理，Shiro 属于轻量框架，越来越多企业项目开始使用 Shiro。

使用 Shiro 实现系统的权限管理，有效提高开发效率，从而降低开发成本。

基本架构：
<div align="center">
<img src="./img/p15.png">
</div>

subject：主体，可以是用户也可以是程序，主体要访问系统，系统需要对主体进行认证、授权。  

**securityManager**：安全管理器，主体进行认证和授权都 是通过 securityManager 进行。

**authenticator**：认证器，主体进行认证最终通过 authenticator 进行的。

**authorizer**：授权器，主体进行授权最终通过authorizer进行的。

sessionManager：Web 应用中一般是用 Web 容器对 session 进行管理，Shiro 也提供一套 session 管理的方式。

SessionDao：通过 SessionDao 管理 session 数据，针对个性化的 session 数据存储需要使用 sessionDao。

cache Manager：缓存管理器，主要对 session 和授权数据进行缓存，比如将授权数据通过 cacheManager 进行缓存管理，和 ehcache 整合对缓存数据进行管理。

**realm**：域，领域，相当于数据源，通过 realm 存取认证、授权相关数据。

cryptography：密码管理，提供了一套加密 / 解密的组件，方便开发。比如提供常用的散列、加 / 解密等功能。


#### 2.2.2 入门案例 1

**第一步**：添加依赖。

```xml
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-core</artifactId>
    <version>1.4.0</version>
</dependency>
<dependency>
    <groupId>commons-logging</groupId>
    <artifactId>commons-logging</artifactId>
    <version>1.2</version>
</dependency>
``` 

**第二步**：使用。

first.int：
```int
[users]
zhang3=123456, role1
li4=654321, role2

[roles]
role1=user:query, user:update
role2=user:delete, user:insert
```


```java
@Test
public void Mytest() {
    // 首先获得 SecurityManager
    IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:first.ini");
    SecurityManager securityManager = securityManagerFactory.getInstance();
    // 获得subject
    SecurityUtils.setSecurityManager(securityManager);
    Subject subject = SecurityUtils.getSubject();
    
    // subject执行认证（login）

    // 提供执行认证的用户名和密码信息，放入到 token 中
    UsernamePasswordToken authenticationToken = new UsernamePasswordToken("zhang3","123456");

    subject.login(authenticationToken); // 通过认证器去执行认证 Authenticator

    boolean authenticated = subject.isAuthenticated(); // 判断是否认证通过
    System.out.println(authenticated);

    // IniRealm → CustomRealm
    // 如果认证不通过，则无法授权
    // 基于角色的授权
    //authorBaseRole(subject);

    // 基于权限的授权
    authorBasePermmison(subject);
}

// 基于角色的授权
private void authorBaseRole(Subject subject) {
    boolean role1 = subject.hasRole("role1");
    System.out.println("是否拥有role1的角色：" + role1);

    ArrayList<String> roleList = new ArrayList<>();
    roleList.add("role1");
    roleList.add("role2");
    boolean[] hasRoles = subject.hasRoles(roleList);
    System.out.println("对 role1、2 分别拥有的角色：" + Arrays.toString(hasRoles));

    boolean hasAllRoles = subject.hasAllRoles(roleList);  // 判断 list 里的权限是否全部拥有
    System.out.println("是否拥有全部角色：" + hasAllRoles);
}

// 基于权限的授权
private void authorBasePermmison(Subject subject) {
    String insertPermission = "user:insert";
    String deletePermission = "user:delete";
    String updatePermission = "user:update";
    String queryPermission = "user:query";
    boolean[] permitteds = subject.isPermitted(insertPermission, deletePermission, updatePermission, queryPermission);
    System.out.println("增删改查多个的权限：" + Arrays.toString(permitteds));

    boolean permitted1 = subject.isPermitted(queryPermission);
    System.out.println("单个权限：" + permitted1);

    boolean permittedAll = subject.isPermittedAll(insertPermission, deletePermission, updatePermission, queryPermission);
    System.out.println("是否拥有全部权限：" + permittedAll);
}
```

#### 2.2.3 入门案例 2

**第一步**：添加依赖。

**第二步**：自定义 realm。

CustomRealm.ini：
```ini
customRealm=com.gyh.realm.CustomRealm
securityManager.realm=$customRealm
```

```java
public class CustomRealm extends AuthorizingRealm {

//    UserMapper userMapper;

    // 处理认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 通过 token 获得用户名信息（token 中的信息就是认证的时传入的信息）
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 这个 username 是接下来要去执行查询的条件 -> 查询当前用户在系统中的密码
        String username = token.getUsername();

        // 通过 name 信息去获得存在于系统或数据库的真实的密码（凭证）信息
        String passwordFromDb = queryPasswordByUsername(username);

        // 第一个参数，是想要存储在系统中的 user 信息 Admin、User、Map、String，是接下来通过 subject 能够获得的用户信息
        User user = new User(username, passwordFromDb);
        // 第二个参数，是用户正确的密码（系统维护的密码）
        // 第三个参数，是 realm 的名字
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, passwordFromDb, this.getName());
        return authenticationInfo;
    }


    // 查询当前用户（已经认证通过的用户）的授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 就是 SimpleAuthenticationInfo 的第一个参数
        User primaryPrincipal = (User) principalCollection.getPrimaryPrincipal();
        // user role permission
        List<String> permissions = queryPermissionByUser(primaryPrincipal.getUsername());

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("role1");
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    private List<String> queryPermissionByUser(String username) {
        ArrayList<String> perms = new ArrayList<>();
        perms.add("user:insert");
        perms.add("user:delete");
        return perms;
    }


    private String queryPasswordByUsername(String username) {
        if ("zhang3".equals(username)){
            return "123456";
        } else {
            return "654321";
        }
    }


}
```

**第三步**：测试。

```java
public class CustomRealmTest {

    @Test
    public void test1(){
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:custom.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();

        subject.login(new UsernamePasswordToken("zhang3","123456"));

        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);

        boolean role1 = subject.hasRole("role1");

        boolean[] permitted = subject.isPermitted("user:insert", "user:delete", "user:query", "user:update");
        System.out.println(Arrays.toString(permitted));
    }
}
```

#### 2.2.4 SpringBoot 中使用 Shiro

**第一步**：添加依赖。

```xml
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-spring</artifactId>
    <version>1.4.1</version>
</dependency>
```

**第二步**：注册组件。


```java
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManagerz){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 认证失败后重定向的 url
        shiroFilterFactoryBean.setLoginUrl("/unAuthc");
        shiroFilterFactoryBean.setSecurityManager(securityManagerz);
        // 最重要的事情
        // 对请求过滤 filter
        // key 对应请求 url，value 对应的是过滤器
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();
        //login(username,password) success
        //失败则重新登录

        filterMap.put("/auth/login","anon");
        filterMap.put("/unAuthc","anon");
        filterMap.put("/index","anon");
        //当你分配了perm1的权限时才能访问need/perm这请求
        //filterMap.put("/need/perm","perms[perm1]");
        //优选的方式是声明式
        //filterMap.put("/auth/logout","logout");
        filterMap.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return shiroFilterFactoryBean;
    }
    //shiro核心组件 SecurityManager

    @Bean
    public DefaultWebSecurityManager securityManagerz(CustomRealm customRealm,DefaultWebSessionManager webSessionManager){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(customRealm);
//        defaultWebSecurityManager.setSessionManager(webSessionManager());
        defaultWebSecurityManager.setSessionManager(webSessionManager);
        return defaultWebSecurityManager;
    }

    /*
    * 声明式鉴权 注解需要的组件
    * */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManagerz){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManagerz);
        return authorizationAttributeSourceAdvisor;
    }

    /*使用映射处理异常：key为异常全类名 value为异常处理的请求*/
    /*@Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.apache.shiro.authz.AuthorizationException","/noperm");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);
        return simpleMappingExceptionResolver;
    }*/

    @Bean
    public DefaultWebSessionManager webSessionManager(){
        CustomSessionManager customSessionManager = new CustomSessionManager();
        return customSessionManager;
    }
}
```

```java
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;

        String username = token.getUsername();
        List<String> strings = userMapper.selectPasswordByName(username);
        String credential =  strings.size() >= 1?strings.get(0):null;

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, credential, this.getName());
        return authenticationInfo;
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        List<String> permissions = userMapper.selectPermissionByUsername(username);

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions(permissions);


        return authorizationInfo;
    }
}
```










## 3 项目常用

### 3.1 跨域

跨域资源共享标准新增了一组 HTTP 首部字段，允许服务器声明哪些源站通过浏览器有权限访问哪些资源。另外，规范要求，对那些可能对服务器数据产生副作用的 HTTP 请求方法（特别是 GET 以外的 HTTP 请求，或者搭配某些 MIME 类型的 POST 请求），浏览器必须首先使用 OPTIONS 方法发起一个预检请求（preflight request），从而获知服务端是否允许该跨域请求。服务器确认允许之后，才发起实际的 HTTP 请求。在预检请求的返回中，服务器端也可以通知客户端，是否需要携带身份凭证（包括 Cookies 和 HTTP 认证相关数据）。

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");    // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*");    // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*");    // 3 设置访问源请求方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }
}
```
或：
```java
@Configuration
public class CORSConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins("*")
            .allowedHeaders("*");
    }
}
```

### 3.2 分页

添加依赖：
```xml
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.5</version>
</dependency>
```

示例：
```java
  public AdminUserVO userList(AdminUserBO userBO) {
      AdminUserVO adminUserVO = new AdminUserVO();

      PageHelper.startPage(userBO.getPage(), userBO.getLimit());
      UserExample userExample = new UserExample();  // 使用由 Generator 工具生成的 bean 工具类
      userExample.setOrderByClause(userBO.getSort() + " " + userBO.getOrder()); // 是一个空格字符串，不是空字符串
      List<User> users = userMapper.selectByExample(userExample);
      PageInfo<User> pageInfo = new PageInfo<User>(users);
      long total = pageInfo.getTotal();

      adminUserVO.setTotal(total);
      adminUserVO.setItems(users);

      return adminUserVO;
  }
  ```

### 3.3 日期格式限定

```java
// 生日格式约束
@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT-8")
private Date birthday;
```

