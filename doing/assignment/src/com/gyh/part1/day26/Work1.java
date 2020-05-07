package com.gyh.part1.day26;

import java.lang.reflect.Field;

public class Work1 {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        TatgetObj tatgetObj = new TatgetObj("oldValue");
        System.out.println("旧的变量值：" + tatgetObj.fieldName);

        Class<?> cls = tatgetObj.getClass();    // 获取类名
        Field field = cls.getDeclaredField("fieldName");    // 获取变量名
        field.set(tatgetObj, "newValue");   // 修改成员变量的新值

        System.out.println("新的变量值：" + tatgetObj.fieldName);
    }
}

class TatgetObj {
    String fieldName;

    TatgetObj(String fieldName) {
        this.fieldName = fieldName;
    }
}
