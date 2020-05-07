package com.gyh.part1.day27;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Solution1 {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, InterruptedException {

        TestTimeLimitFactory testTimeLimitFactory = new TestTimeLimitFactory(TestTimeLimit.class);

        TestTimeLimit clsObj = new TestTimeLimit();
        boolean is = testTimeLimitFactory.isLimit("run", clsObj);
        System.out.println(is);
    }
}

class TestTimeLimit {
    @RunTimeLimit(200)
    public void run() throws InterruptedException {
        Thread.sleep(300);
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface RunTimeLimit {
    // 定义一个属性，来描述，方法运行时间的上限
    long value();
}

class TestTimeLimitFactory {
    Class cls;

    public TestTimeLimitFactory( Class cls) {
        this.cls = cls;
    }

    public boolean isLimit(String str, Object clsObj) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method targetMethod = cls.getDeclaredMethod(str);   // 获取方法

        long value = getTimeLimit(targetMethod);
        if (value == -1) {
            return false;   // 没有注解也就不会超时
        }

        targetMethod.setAccessible(true);
        long startTime = System.currentTimeMillis();
        targetMethod.invoke(clsObj);
        long endTime = System.currentTimeMillis();

        return endTime - startTime > value;
    }

    private long getTimeLimit(Method targetMethod) {
        if (targetMethod.isAnnotationPresent(RunTimeLimit.class)) {
            RunTimeLimit annotation = targetMethod.getAnnotation(RunTimeLimit.class);
            long value = annotation.value();
            return value;
        } else {
            return -1;
        }
    }
}