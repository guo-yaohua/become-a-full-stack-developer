use stu_db;

# a.查询男生、女生人数
select sgender, count(*)
from student
group by sgender;

# b.查询名字中含有「风」字的学生信息
select *
from student
where sname like '%风%';

# c.查询同名同姓的学生名单，并统计同名的人数
select distinct sname, snum
from student join (
	select count(*) as snum, sname
    from student
    group by sname
) as tmp using(sname)
where snum > 1;

# d.查询每门课程的平均成绩，按平均成绩降序排列，平均成绩相同时，按课程编号升序排列
select cid, avg(score) as avg_score
from sc
group by cid
order by avg_score desc, cid asc;

# e.查询平均成绩大于等于 85 的学生信息
select student.*, avg_score
from student join (
	select sid, avg(score) as avg_score
    from sc
    group by sid
) as tmp using(sid)
where avg_score >= 85;

# f.查询「数学」不及格的学生信息
select student.*
from student join (
	select sid
    from sc left join course on sc.cid = course.cid
    where course.cname = '数学' AND score < 60
) as tmp using(sid);

# g.查询有挂科学生的课程信息以及任课老师
select distinct course.*, tname
from course join (
	select cid
    from sc
    where score < 60
) as tmp using(cid)
left join teacher on course.tid = teacher.tid;