package com.gyh.part1.day27;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Test {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        // 创建注解处理器对象
        StudentFactory studentFactory = new StudentFactory();

        int age = 35;
        String name = "张三";
        Student student = studentFactory.getStudent(age, name);
        System.out.println(student);
    }
}

class Student {

    @AgeConstraint(minAge = 18, maxAge = 25)
    int age;

    @NameLengthConstraint(lengthLimit = 5)
    String name;

    private Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

@Retention(RetentionPolicy.RUNTIME)
@interface AgeConstraint {
    int maxAge();
    int minAge();
}

@Retention(RetentionPolicy.RUNTIME)
@interface NameLengthConstraint {
    int  lengthLimit();
}

/*  StudentFactory 就是用来接收 name 和 age 的额外约束信息，并处理额外信息的注解处理器。实现如下效果：
 *   1. 只有初始化参数，name 和 age 满足约束条件的时候，才能成功创建 Student 对象
 *   2. 否则，拒绝创建，并抛出异常（根据约束信息，要实现的特殊效果）
 */
class StudentFactory {

    // 目标 Class 对象，因为注解实例放在类定义中使用
    private Class studentClz;

    public StudentFactory() {
        this.studentClz = Student.class;  // 操作对象为 Student，因此直接获取
    }

    // 其他人可以通过调用该方法，获取新创建的 Student 对象
    // 同时，该方法接收，待创建的 Student 对象的两个初始化参数
    public Student getStudent(int age, String name)
            throws NoSuchFieldException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        // 判断参数值，如果不满足约束，直接抛出异常
        judgeAge(age);

        judgeNameLength(name);

        // 通过反射，创建 Student 对象，并且用合法的参数值，初始化该对象，并返回
        Constructor con = studentClz.getDeclaredConstructor(int.class, String.class);
        con.setAccessible(true);
        Student st = (Student) con.newInstance(age, name);

        return st;
    }

    /**
     * 用来判断 age 参数是否满足约束
     * @param age 待判断的参数
     */
    private void judgeAge(int age) throws NoSuchFieldException {

        // 表示 age 成员变量约束条件的注解对象，属于 age 成员变量信息
        Field ageField = studentClz.getDeclaredField("age");

        //提前解决权限问题
        ageField.setAccessible(true);

        // isAnnotationPresent 判断在成员变量中，是否包含目标类型的注解信息
        if (ageField.isAnnotationPresent(AgeConstraint.class)) {
            // 获取指定类型的注解实例
            AgeConstraint ageAnnotation = ageField.getAnnotation(AgeConstraint.class);

            // 从形式上来看，要获取哪个数值，就在注解实例上调用那个属性对应的方法
            int maxAge = ageAnnotation.maxAge();
            int minAge = ageAnnotation.minAge();

            // 判断参数是否满足条件
            if (age < minAge || age > maxAge) {
                // 如果参数不满足约束条件就抛出异常
                throw new IllegalArgumentException("age参数非法：age = " + age);
            }
        }
    }

    /**
     * 判断，name 参数，长度是否满足约束条件
     * @param name 带判断的参数值
     */
    private void judgeNameLength(String name) throws NoSuchFieldException {

        Field nameField = studentClz.getDeclaredField("name");

        if (nameField.isAnnotationPresent(NameLengthConstraint.class)) {
            NameLengthConstraint nameAnnotation = nameField.getAnnotation(NameLengthConstraint.class);

            int lengthLimit = nameAnnotation.lengthLimit();

            if (name.length() > lengthLimit) {
                throw new IllegalArgumentException("name参数非法: name = " + name);
            }
        }
    }
}
