package com.gyh.part1.day26;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Work2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        callTargetMethod("config.properties");
    }

    public static void callTargetMethod(String configFilePath) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(configFilePath);
        properties.load(fis);

        String className = properties.getProperty("className"); // 类名
        String methodName = properties.getProperty("methodName");   // 方法名

        Class targetClass = Class.forName(className);
        Method method = targetClass.getDeclaredMethod(methodName);
        method.setAccessible(true);

        Constructor constructor = targetClass.getDeclaredConstructor();
        Object helloWorld = constructor.newInstance();
        method.invoke(helloWorld);
    }
}

class HelloWorld {
    static void helloWorld() {
        System.out.println("Hello World!");
    }
}