# 十一、MyBatis

## 目录

- [十一、MyBatis](#十一mybatis)
  - [目录](#目录)
  - [1 MyBatis](#1-mybatis)
    - [1.1 MyBatis 概述](#11-mybatis-概述)
    - [1.2 入门案例](#12-入门案例)
    - [1.3 MyBatis 配置](#13-mybatis-配置)
      - [1.3.1 properties](#131-properties)
      - [1.3.2 settings](#132-settings)
      - [1.3.3 typeAliases](#133-typealiases)
      - [1.3.4 typehandler](#134-typehandler)
      - [1.3.5 mappers](#135-mappers)
  - [2 Log4j](#2-log4j)

## 1 MyBatis

### 1.1 MyBatis 概述

MyBatis 是一款优秀的持久层框架，它支持定制化 SQL（灵活的修改）、存储过程（函数）以及高级映射（javabean 和数据库对象的映射，输入映射和输出映射）。  
MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。  

特点：
- 定制化 sql 更能适应业务复杂度。

- 高级映射：输入映射和输出映射。  
  - 输入映射：对象 -> 关系表，为预编译的sql提供参数。

  - 输出映射：关系表 -> 对象，查询结果的封装。

- 动态 sql：根据参数的不同可以动态的更改执行的 sql 语句。

- 分离 sql，集中管理。通过映射文件，分别管理不同的模块。

- MyBatis 对参数和结果的封装处理更完善，直接封装成对应的类型，使用更方便。

MyBatis 可以使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs 映射成数据库中的记录（ORM和持久化）。

ORM（Object Relational Mapping）框架：
- Object：javabean。
- relation：关系表。
- Mapping：一个 javabean 对象就可以对应一条关系表中的记录。


### 1.2 入门案例

将 sql 语句集中管理到映射文件中，然后去调用映射文件中的 sql。

**第一步**：导入依赖。

```xml
<dependencies>
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.4</version>
    </dependency>

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.12</version>
    </dependency>
</dependencies>
```

**第二步**：引入 MyBatis 的配置文件。

MyBatis 配置模板：
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
  PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
  "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
  <environments default="development">
    <environment id="development">
      <transactionManager type="JDBC"/>
      <dataSource type="POOLED">
        <property name="driver" value="${driver}"/>
        <property name="url" value="${url}"/>
        <property name="username" value="${username}"/>
        <property name="password" value="${password}"/>
      </dataSource>
    </environment>
  </environments>
  <mappers>
    <mapper resource="org/mybatis/example/BlogMapper.xml"/>
  </mappers>
</configuration>
```

新建 MyBatis 配置：
<div align="center">
<img src="./img/p1.png">
</div>

注：第一次配置时 `http://mybatis.org/dtd/mybatis-3-mapper.dtd` 会报红，需要在 IDEA 中添加配置：  

<div align="center">
<img src="./img/p2.png">
</div>

`<mappers>` 标签中的 `resource` 属性加载映射文件。
```xml
<mappers>
    <mapper resource="mapper/UserMapper.xml"/>
</mappers>
```

映射文件：
```xml
<!-- 示例：resources/mapper/UserMapper.xml -->
<!-- 映射文件的名字：xxxMapper -->

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace就是该映射文件的唯一id，映射文件的namespace(命名空间)不能重复-->
<mapper namespace="userMapper">
<!-- sql 语句 -->
</mapper>
```

**第三步**：映射文件中使用 slq 语句。

```xml
<mapper namespace="userMapper">

    <!--select 意味着 sql 语句是查询-->
    <!--id 是该 sql 语句在该映射文件下的唯一标识，这个 id 在这个映射文件中是唯一的不能重复-->
    <!--resultType 查询结果封装的类型-->
    <!--占位符的写法 #{}-->
    <select id="selectUsernameById" resultType="java.lang.String">
        select username from user_tb where id = #{id}
    </select>

    <!--selectList 仍然使用的是 select 标签-->
    <!--查询多条记录，resultType 仍然写的是单条记录的类型-->
    <select id="selectUsernames" resultType="java.lang.String">
        select username from user_t
    </select>

    <!--insert 标签做新增操作-->
    <!--增删改都不需要也写不了 resultType-->
    <!--如果传入的 Object 参数类型是 javabean 或 map 对象，#{} 中使用 javabean 的成员变量名或 map 的 key-->
    <insert id="insertUser">
        insert into user_t (id, username, password, age, gender) value
        (#{id}, #{username}, #{password}, #{age}, #{gender})
    </insert>

    <update id="updateUser">
        update user_t set password = #{password} where id = #{id}
    </update>

    <delete id="deleteUserById">
        delete from user_t where id = #{zzz}
    </delete>
</mapper>
```

**第四步**：测试。

```java
public class MyBatisTest {
    static SqlSessionFactory sqlSessionFactory;
    SqlSession sqlSession = null;
    
    @BeforeClass
    public static void beforeClass() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // sqlSessionFactory 线程安全，只初始化一次
        sqlSessionFactory = builder.build(inputStream);
    }

    @Before // 每一个 @Test 方法执行之前都会执行的方法
    public void init() {
        // 线程不安全，每个单元测试方法都要初始化一个新的 sqlSession
        sqlSession = sqlSessionFactory.openSession();
    }

    @After
    public void commit(){
        if (sqlSession != null) {
            sqlSession.commit();
            sqlSession.close();
        }
    }


    /**
     * select
     */
    @Test
    public void mytest() throws IOException {

        // SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        // InputStream inputStream = Resources.getResourceAsStream("mybatis.xml");
        // SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        // SqlSession sqlSession = sqlSessionFactory.openSession();

        String sqlId = "userMapper" + "." + "selectUsernameById";
        String username = sqlSession.selectOne(sqlId, 2);
        System.out.println("username = " + username);
    }

    @Test
    public void mytest2() throws IOException {

        List<String> usernames = sqlSession.selectList("userMapper.selectUsernames");
        System.out.println(usernames);
    }

    /**
     * insert
     */
    @Test
    public void mytest3() throws IOException {
        User user = new User(3, "li4", "123456", 20, "male");
        int insert = sqlSession.insert("userMapper.insertUser", user);
        System.out.println("insert = " + insert);
    }

    /**
     * update
     */
    @Test
    public void mytest4(){
        Map map = new HashMap();
        map.put("id", 3);
        map.put("password", "654321");
        int update = sqlSession.update("userMapper.updateUser", map);
    }

    /**
     * delete
     */
    @Test
    public void mytest5(){
        int delete = sqlSession.delete("userMapper.deleteUserById", 3);
        System.out.println("delete = " + delete);
    }
}
```

### 1.3 MyBatis 配置

#### 1.3.1 properties

给 MyBatis 中的配置项（mybatis.xml）提供参数。

**（1）单项配置**

示例：
```xml
<properties>
    <!--name 方便配置文件引用值-->
    <property name="driver" value="com.mysql.jdbc.Driver"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/test_db"/>
</properties>
```

使用：
```xml
<property name="driver" value="${driver}"/>
<property name="url" value="${jdbcUrl}"/>
```

**（2）批量配置**

引入一个 properties 配置文件。

示例：
```xml
<properties resource="db.properties"/>
```

使用：
```xml
<property name="driver" value="${db.driver}"/>
<property name="url" value="${db.jdbcUrl}"/>
```

注：properties 配置文件中不需要使用转义字符。

#### 1.3.2 settings

```xml
<settings>
    <setting name="" value="">
</settings>
```

具体配置参考：[官方文档](https://mybatis.org/mybatis-3/zh/configuration.html#settings)。

#### 1.3.3 typeAliases

类型别名可为 Java 类型设置一个缩写名字。它仅用于 XML 配置，意在降低冗余的全限定类名书写。

**（1）单项配置**  

示例：
```xml
<typeAliases>
    <!--type 写全类名，alias 写的就是别名-->
    <typeAlias type="com.gyh.bean.User" alias="userz"/>
</typeAliases>
```

使用：
```xml
<mapper namespace="userMapper">
    <!--配置了别名之后，既可以写全类名，也可以写别名-->
    <select id="selectUserById" resultType="userz">
        select id,username,password,age,gender from user_t where id = #{id}
    </select>
</mapper>
```

**（2）多项配置**  

示例：
```xml
<typeAliases>
    <!--name 写包名，则包下所有的 javabean 都给配置了别名，规律：类名的纯小写-->
    <package name="com.gyh.bean"/>
</typeAliases>
```

**（3）MyBatis 提供的别名**

基本类型、包装类、java.lang 目录下的类。  

| 别名 | 映射的类型 |
| :- | :- |
| _byte | byte |
| _long | long |
| _short | short |
| _int | int |
| _integer | int |
| _double | double |
| _float | float |
| _boolean | boolean |
| string | String |
| byte | Byte |
| long | Long |
| short | Short |
| int | Integer |
| integer | Integer |
| double | Double |
| float | Float |
| boolean | Boolean |
| date | Date |
| decimal | BigDecimal |
| bigdecimal | BigDecimal |
| object | Object |
| map | Map |
| hashmap | HashMap |
| list | List |
| arraylist | ArrayList |
| collection | Collection |
| iterator | Iterator |

#### 1.3.4 typehandler

MyBatis 在设置预处理语句（PreparedStatement）中的参数或从结果集中取出一个值时，都会用类型处理器将获取到的值以合适的方式转换成 Java 类型。

#### 1.3.5 mappers

**（1）逐项配置**  

resource：
```xml
<mappers>
    <mapper resource="mapper/UserMapper.xml"/>
</mappers>
```

url：
```xml
<mappers>
    <!--url属性：加载的是文件路径下的映射文件-->
    <mapper url="file:///D:\MyWorkSpace\xxx\mapper\UserMapper.xml"/>
</mappers>
```

class：
```xml
<mappers>
    <!--class 属性：-->
    <!--接口和映射文件
        1、接口和映射文件要在同一级 classpath 目录下，并且同名
        2、映射文件的 namespace 值和接口的全类名一致
        3、接口中的方法名和 crud 标签的 id 一致
        分析：namespace.id → 接口的全类名 + 接口中的方法名
    -->
    <mapper class="com.gyh.mapper.UserMapper"/>
</mappers>
```

使用：
```java
@Test
public void myTest(){
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    User user = mapper.selectUserById(1);
}
```


**（2）批量配置**  

示例：
```xml
<mappers>
    <package name="com.gyh.mapper"/>
</mappers>
```

## 2 Log4j

MyBatis 本身支持了 log4j，可以直接使用。

**第一步**：新增依赖。

```xml
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```

**第二步**：复制配置文件。

log4j.properties：
```properties
#Appender
#org.apache.log4j.ConsoleAppender（控制台）
#org.apache.log4j.FileAppender（文件）
#org.apache.log4j.DailyRollingFileAppender（每天产生一个日志文件）
#org.apache.log4j.RollingFileAppender（文件大小到达指定尺寸的时候产生一个新的文件）
#org.apache.log4j.WriterAppender（将日志信息以流格式发送到任意指定的地方）

### direct log messages to stdout ###
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n

### direct messages to file hibernate.log ###
log4j.appender.file=org.apache.log4j.FileAppender
log4j.appender.file.File=d://mybatis.log
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n

#org.apache.log4j.HTMLLayout（以 HTML 表格形式布局）
#org.apache.log4j.PatternLayout（可以灵活地指定布局模式）
#org.apache.log4j.SimpleLayout（包含日志信息的级别和信息字符串）
#org.apache.log4j.TTCCLayout（包含日志产生的时间、线程、类别等信息）
log4j.appender.file2 = org.apache.log4j.FileAppender
log4j.appender.file2.File=d://file2.log
log4j.appender.file2.layout=org.apache.log4j.SimpleLayout
#log4j.appender.file2.layout.ConversionPattern=%d{ABSOLUTE} %5p %c{1}:%L - %m%n

### DEBUG、INFO、WARN、ERROR 和 FATAL
# 日志级别 + 输出 appender
log4j.rootLogger=debug, file, stdout, file2
```
