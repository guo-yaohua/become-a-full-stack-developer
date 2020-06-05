package com.gyh.test;

import org.junit.Test;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;

public class FooDemo {
    @Test
    public void foo() throws SQLException {
        // 注册驱动
        Driver driver = new com.mysql.jdbc.Driver();

        // 获取连接
        String url = "jdbc:mysql://localhost:3306/jdbc_db";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "r00tme");
        Connection conn = driver.connect(url, info);

        // 获取SQL执行平台
        Statement stmt = conn.createStatement();

        // 执行SQL
        String sql = "select * from t_user";
        ResultSet rs = stmt.executeQuery(sql);

        // 处理结果
        while (rs.next()) {
            // MySQL索引是从1开始的
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String password = rs.getString(3);
            BigDecimal balance = rs.getBigDecimal(4);
            System.out.println(id + " " + name + " " + password + " " + balance);
        }

        // 断开连接，释放资源
        rs.close();
        stmt.close();
        conn.close();
    }
}