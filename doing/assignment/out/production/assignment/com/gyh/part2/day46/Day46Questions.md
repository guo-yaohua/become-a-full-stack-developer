# Day19_SQL04

1. 将今天讲的内容总结，并把我上课演示的SQL语句重写一遍(做完之后，再做后面的习题)。

2. 在student, course, teacher, sc 表中，完成下面查询。  
    a.查询所有同学的学生编号、学生姓名、选课总数、所有课程的总成绩(没成绩的显示为 null)
    
    b.查询'01'课程分数小于60的学生信息, 并按照分数降序排序
    
    c.查询只选修两门课程的学生信息。
    
    d. 查询1990年出生的学生信息。
    
    e. 查询本月过生日的学生信息。
    
    f. 查询没上过'张三'老师课程的学生信息。

3. 现今有一张员工表如下：  
employees (employee_id, first_name, last_name, salary, manager_id, department_id)  
注意：manager 也是 employee。
请完成如下查询：  
    ① 查询和Zlotkey相同部门的员工名字和工资 (last_name, salary)
    
    ② 查询工资比公司平均工资高的员工的员工号，名字和工资 (employee_id, last_name, salary)
    
    ③ 查询各部门中工资比本部门平均工资高的员工的员工号, 名字和工资 (employee_id, last_name, salary)
    
    ④ 查询和名字中包含字母u的员工在相同部门的员工的员工号和姓名 (employee_id, last_name)
    
    ⑤ 查询管理者是King的员工名字和工资 (last_name, salary)
    
    ⑥ 查询工资最高的员工的姓名，要求first_name和last_name显示为一列，列名为'姓.名'