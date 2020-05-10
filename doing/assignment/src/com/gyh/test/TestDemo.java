package com.gyh.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDemo {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        System.out.println("默认：" + date);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        System.out.println("格式处理：" + simpleDateFormat.format(date));

        date = simpleDateFormat.parse("2020/01/10 17:06:00");
        System.out.println("解析字符串：" + date);
    }
}

