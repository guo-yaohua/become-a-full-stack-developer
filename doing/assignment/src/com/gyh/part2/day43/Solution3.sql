# 存在就删除
drop database if exists day43;

# 创建数据库
create database day43 character set utf8mb4 collate utf8mb4_bin;

# 创建表
use day43;

create table athletes (
	id int,
    name varchar(255),
    gender boolean,
    height int,
    position ENUM('Point Guard', 'Shooting Guard', 'Small Forward', 'Power Forward', 'Center')
);

# 插入数据
INSERT INTO	athletes
VALUES (1, '张三', true, 180, 'Point Guard'),
	(2, '李四', false, 160, 'Shooting Guard'),
    (3, '王二', true, 170, 'Power Forward'),
    (4, '麻子', true, 180, 'Small Forward'),
    (5, '第五', false, 170, 'Center');