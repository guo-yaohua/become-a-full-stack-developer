use stu_db;

# a. 查询平均成绩大于等于 60 分的同学信息
select student.*, avg_score 
from student join (
	 select sid, avg(score) as avg_score
	 from sc
	 group by sid
) as tmp using(sid)
where avg_score > 60;
  
# b. 查询有成绩的学生信息
select student.*
from student join (
	select distinct sid
    from sc
) as tmp using(sid);

# c. 查询选修「张三」老师课的同学信息，课程编号和课程分数
select student.*, cid, score
from student join (
	select sid, cid, score
    from sc join (
		select cid
        from course
        where tid = (
			select tid
            from teacher
			where tname='张三'
        )
	) as tn using(cid)
) as s using(sid);

# d. 查询没有选修所有课程的学生信息
select distinct student.*
from student left join (
	select sid, count(cid) as cnm
    from sc
    group by sid
) as tmp using(sid)
where cnm is null OR cnm < (
	select count(*)
    from course
);

# e. 查询 '01' 课程不及格同学的信息，并按照课程分数降序排列
select student.*, cid, score
from student join (
	select sid, cid, score
    from sc
    where cid = '01' AND score < 60
) as tmp using(sid)
order by score desc; 

# f. 查询各学生的年龄，按年份来算
select student.*, year(now()) - year(sage) as age
from student;