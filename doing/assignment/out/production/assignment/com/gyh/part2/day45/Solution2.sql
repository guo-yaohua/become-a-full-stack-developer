drop database if exists company;

create database company;

use company;

create table department (
	department_name varchar(20) not null,
    leader_name varchar(20) not null,
    
    primary key(department_name)
);

create table employee (
	employee_name varchar(20) not null,
    age int not null,
    gender enum('男', '女') default '男',
    department_name varchar(20),
    salary int,
    phone_number int unique,
    
    primary key(employee_name),
    constraint fk_department foreign key(department_name) references department(department_name)
);

alter table department add constraint fk_leader foreign key(leader_name) references employee(employee_name);