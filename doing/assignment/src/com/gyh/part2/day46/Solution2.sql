create database stu_db;
use stu_db;

create table student(
	sid varchar(10),
    sname varchar(10),
    sage datetime,
    sgender varchar(10)
);
insert into student values('01' , '赵雷' , '1990-01-01' , '男');
insert into student values('02' , '钱电' , '1990-12-21' , '男');
insert into student values('03' , '孙风' , '1990-12-20' , '男');
insert into student values('04' , '李云' , '1990-12-06' , '男');
insert into student values('05' , '周梅' , '1991-12-01' , '女');
insert into student values('06' , '吴兰' , '1992-01-01' , '女');
insert into student values('07' , '郑竹' , '1989-01-01' , '女');
insert into student values('09' , '张三' , '2017-12-20' , '女');
insert into student values('10' , '李四' , '2017-12-25' , '女');
insert into student values('11' , '李四' , '2012-06-06' , '女');
insert into student values('12' , '赵六' , '2013-06-13' , '女');
insert into student values('13' , '孙七' , '2014-06-01' , '女');

create table course(
	cid varchar(10),
    cname varchar(10),
    tid varchar(10)
);
insert into course values('01' , '语文' , '02');
insert into course values('02' , '数学' , '01');
insert into course values('03' , '英语' , '03');

create table teacher(
	tid varchar(10),
    tname varchar(10)
);
insert into teacher values('01' , '张三');
insert into teacher values('02' , '李四');
insert into teacher values('03' , '王五');

create table sc(
	sid varchar(10),
    cid varchar(10),
    score float(5,2)
);
insert into sc values('01' , '01' , 80);
insert into sc values('01' , '02' , 90);
insert into sc values('01' , '03' , 99);
insert into sc values('02' , '01' , 70);
insert into sc values('02' , '02' , 60);
insert into sc values('02' , '03' , 80);
insert into sc values('03' , '01' , 80);
insert into sc values('03' , '02' , 80);
insert into sc values('03' , '03' , 80);
insert into sc values('04' , '01' , 50);
insert into sc values('04' , '02' , 30);
insert into sc values('04' , '03' , 20);
insert into sc values('05' , '01' , 76);
insert into sc values('05' , '02' , 87);
insert into sc values('06' , '01' , 31);
insert into sc values('06' , '03' , 34);
insert into sc values('07' , '02' , 89);
insert into sc values('07' , '03' , 98);

# a.查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩(没成绩的显示为 null)
select sid, sname, cnum, total_score
from student left join (
	select sid, count(*) as cnum, sum(score) as total_score 
	from sc group by sid
) as tmp using(sid);

# b.查询'01'课程分数小于60的学生信息, 并按照分数降序排序
select student.*, score 
from student join sc using(sid)
where cid='01' and score < 60
order by score desc;

# c.查询只选修两门课程的学生信息。

select sid, sname, cnum
from student left join (
	select sid, count(*) as cnum
	from sc group by sid
) as tmp using(sid)
where cnum = 2;

# d. 查询1990年出生的学生信息。
select *
from student
where year(sage)='1990';

# e. 查询本月过生日的学生信息。
select *
from student
where month(sage) = month(now());

# f. 查询没上过'张三'老师课程的学生信息。
select * 
from student 
where sid not in (
	select sid
	from student
	join sc using(sid)
	join course using(cid)
	join teacher using(tid)
	where tname='张三'
);