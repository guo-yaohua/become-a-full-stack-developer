# 数据库

## 目录

## ## 数据库基础

数据库定义：  
> A database is an organized collection of data, stored and accessed electronically.  


分类：
- 关系型数据库。  
  不仅存储数据本身，还存储数据之间的关系，比如说用户信息和订单信息。关系型数据库模型把复杂的数据结构归结为简单的二维表（关系表）。

- 非关系型数据库。  
  非关系型数据库也被称为 NoSQL 数据库。NoSQL 的产生并不是要否定关系型数据库，而是作为关系型数据库的一个有效补充。

易混淆术语：
- 数据库系统（DBS）：是指在计算机系统中引入数据库后的系统，一般由数据库、数据库管理系统、应用系统、数据库管理员（DBA）构成。 

- 数据库管理系统（DBMS）：是一种操纵和管理数据库的大型软件，用于建立、使用和维护数据库（如：MySQL）。 

- 数据库（DB）：数据库是按照数据结构来组织、存储和管理数据的仓库。

注：我们通常用数据库这个术语来代表 DBMS，严格来说这是不正确的，容易产生混淆。

数据库管理系统、数据库服务、数据库和表的关系（C/S 架构）：  
<div align="center">
<img src="./img/p1.png">
</div>

数据在表中的形式：  
<div align="center">
<img src="./img/p2.png">
</div>  

- 表的一行称之为一条记录（数据）。

- 表中一条记录对应一个 Java 对象的数据。  
  - 对象与行对应，属性与列对应。


## MySQL

### MySQL安装与配置

具体步骤查看 PDF 文件：<a href="./file/MySQL安装与配置.pdf" download="MySQL安装与配置"> MySQL 安装与配置 </a>

### 登录 MySQL Server

在启动 MySQL 服务后，输入以下格式的命令：
```
mysql -h 主机名 -u 用户名 –p
```
- `-h`：该参数用于指定客户端的主机名（host），即哪台机器要登录 MySQL Server，如果是当前机器该参数可以省略。

- `-u`：用户名（user）。

- `-p`：密码（password），如果密码为空，该参数可以省略。

示例：
```
mysql -u root -p
```

## SQL

### SQL简介

SQL 是结构化查询语言（Structured Query Language）的缩写。它是一种专门用来与关系型数据库沟通的语言。

SQL 语言的优点：  
- 通用。  
  几乎所有的关系型数据库都支持 SQL。

- 简单易学。  
  它的语句是由一些有很强描述性的关键词组织而成，
而且这些关键词并不多。

- 简单且强有力。  
  灵活地使用 SQL，可以进行非常复杂的数据库操作。 

- 半衰期很长。

SQL 不区分大小写。  
注：  
- 虽然 SQL 不区分大小写，但是表名、列名和值可能区分！（这依赖具体的 DBMS 及其配置）。
SQL 不区分大小写（关键字不区分大小写）！！！

- 建议：关键字大写；表名、列名和值最好是以它定义时为准。

> 标准 SQL 是由 ANSI 标准委员会管理的，从而称为 ANSI SQL。许多 DBMS 厂商通过增加语句或指令，对 SQL 进行了扩展，目的是提供一些特定的操作，或者是简化某些操作。虽然这种扩展很有必要，但同时也给 SQL 代码的移植带来了麻烦。   
> 即使 DBMS 有自己的扩展，但它们都支持 ANSI SQL。

组成：
- DDL：数据定义语言。

- DML：数据操作语言（增、删、改）。

- DQL：数据查询语言（查）。

- DCL：数据控制语言。

- TPL：事务处理语言。

- …

### 数据库操作

数据定义语言 DDL（Data Definition Language）。
- 作用：创建 & 管理数据库和表的结构。

- 常用关键字：CREATE、ALTER 和 DROP。

**（1）创建数据库**  

```sql
CREATE DATABASE [IF NOT EXISTS] db_name [create_specification [ create_specification] ...];
```

create_specification：
- `CHARACTER SET charset_name`：指定字符集，如 utf8。

- `COLLATE collation_name`：指定数据库字符集的比较方式。  
  
  比较规则有时也称为排序规则，用于在字符串比较大小时规定其顺序的。  
  同一种字符集可以有多种比较规则。

  比较规则名称以对应的字符集名称开头，中间部分表示主要用于哪种语言，常见后缀有以下几种：
  - `_bin`：以二进制方式比较。即 A < a。

  - `_ci`：不区分大小写。即 A = a。  
  
  默认 `_ci`。

示例：
```sql
# 创建一个名称为 mydb1 的数据库。
create database mydb1;

# 创建一个使用 gbk 字符集的 mydb2 数据库。
create database if not exists mydb2 character set gbk;

# 创建一个使用 gbk 字符集，并带校对规则（gbk_bin）的 mydb3 数据库。
create database mydb3 character set gbk collate gbk_bin;
```

**（2）查看、删除数据库**  

显示数据库语句：`SHOW DATABASES`。  
示例：
```sql
# 查看所有数据库
show databases;
```

显示数据库创建语句：`SHOW CREATE DATABASE db_name`。  
示例：
```sql
# 查询数据库的创建语句 
show create database mydb1;
```
 
数据库删除语句：`DROP DATABASE [IF EXISTS] db_name`。  
示例：
```sql
# 删除数据库 mydb3
drop database if exists mydb3;
```

**（3）修改数据库**  

```sql
ALTER  DATABASE db_name [alter_specification [, alter_specification] ...] 
```

alter_specification:    
- `CHARACTER SET charset_name`。

- `COLLATE collation_name`。

示例：
```sql
# 把 mydb2 的字符集修改为 utf8
alter database mydb2 character set utf8;
```

### 表操作

**（1）创建表**  

```sql
CREATE TABLE table_name
(
	field1  datatype,
	field2  datatype,
	field3  datatype
)[CHARACTER SET 字符集 COLLATE 校对规则]
```
- field：指定列名。

- datatype：指定列类型。

注：
- 创建表前，要先使用 `USE db_name` 语句使用库。  

- 创建表时，要根据需保存的数据创建相应的列，并根据数据的类型定义相应的列类型。

示例：
```sql
# 在数据库 mydb1 中 创建 user 表
use mydb1;

create table t_user (
	id int,
    name varchar(255),
    password varchar(20),
    birthday date
);
```

**（2）查询表**  

简单描述表结构：`DESC 表名` 或者 `DESCRIBE 表名`。  
示例：
```sql
# 查看数据库中所有的表
show tables;
```


查看生成表的 DDL 语句：`SHOW CREATE TABLE 表名`。  
示例：
```sql
# 查看表的定义语句
show create table t_user;
```

**（3）修改表**  

使用 `ALTER TABLE` 语句追加、修改或删除列的语法。  

追加：
```sql
ALTER TABLE table_name ADD (column datatype [DEFAULT expr] [,ADD column datatype]...);
```
示例：
```sql
# 添加 gender 列
alter table t_user add column gender varchar(10);

# 在 password 后面添加 balance 列
alter table t_user add column balance int after password;

# 在最前面添加 no 列
alter table t_user add column no int first;

# 添加 a, b 列 
alter table t_user add column a int, add column b int;
```

修改：
```sql
ALTER TABLE table_name change col_name new_col_name datatype [DEFAULT expr] [,change col_name new_col_name datatype [DEFAULT ...] ]...;
```
示例：
```sql
# 把 balance 的名字改成 salary
alter table t_user change column balance salary int;
```

修改定义：
```sql
ALTER TABLE table_name MODIFY column datatype [DEFAULT expr] [,MODIFY column datatype]...;
```
示例：
```sql
# 把 salary 的类型改成 decimal(10,2)
alter table t_user modify column salary decimal(10,2);
```

删除：
```sql
ALTER TABLE table_name DROP column col_name;
```
示例：
```sql
# 删除 a 列
alter table t_user drop column a;
```

注：
- 修改表的名称：`RENAME TABLE 表名 TO 新表名`。  
  
  RENAME TABLE 语句的另一个用法是移动该表到另一个数据库。格式：`RENAME TABLE 旧数据库名.旧表名 TO 新数据库名.新表名`。  

- 修改表的字符集：`alter table student character set utf8;`。

多个修改的操作可以同时在同一 `ALTER TABLE` 语句后。  
示例：
```sql
# 删除 b 列, 把 gender 的类型改成 bit(1), 在 name 的后面添加 c 列
alter table t_user drop column b, modify column gender bit(1), add column c int after name;
```


**（4）删除表**  

格式：`DROP TALBE 表名`。  
示例：
```sql
# 删除表 
drop table t_user;
```