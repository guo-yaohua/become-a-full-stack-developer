drop database if exists emp_db;
create database emp_db;
use emp_db;

create table employees (
	employee_id varchar(10), 
    first_name varchar(10), 
    last_name varchar(10), 
    salary int, 
    manager_id varchar(10), 
    department_id varchar(10)
);
insert into employees values('01', 'zhang', '3', 10000, '02', '1000');
insert into employees values('02', 'li', 'yu', 20000, null, '1000');
insert into employees values('03', 'wang', 'King', 20000, null, '1002');
insert into employees values('04', 'ma', 'Zlotkey', 10000, '03', '1002');


# ① 查询和Zlotkey相同部门的员工名字和工资 (last_name, salary)
select last_name, salary
from employees
where department_id = (
	select department_id
	from employees
	where last_name = 'Zlotkey'
);

# ② 查询工资比公司平均工资高的员工的员工号，名字和工资 (employee_id, last_name, salary)
select employee_id, last_name, salary
from employees
where salary > (
	select avg(salary)
    from employees
);

# ③ 查询各部门中工资比本部门平均工资高的员工的员工号, 名字和工资 (employee_id, last_name, salary)
select employee_id, last_name, salary
from employees as a
where salary > (
	select avg(salary)
    from employees as b
    where b.department_id = a.department_id
);

# ④ 查询和名字中包含字母u的员工在相同部门的员工的员工号和姓名 (employee_id, last_name)
select employee_id, last_name
from employees
where department_id IN (
	select department_id
	from employees
	where last_name LIKE '%u%'
);

# ⑤ 查询管理者是King的员工名字和工资 (last_name, salary)
select last_name,salary
from employees
where manager_id IN (
	select employee_id
	from employees
	where last_name = 'King'
);

# ⑥ 查询工资最高的员工的姓名，要求first_name和last_name显示为一列，列名为'姓.名'
select CONCAT(first_name,'.',last_name) "姓.名"
from employees
where salary=(
	select max(salary)
    from employees
);