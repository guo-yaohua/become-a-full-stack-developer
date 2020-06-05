package com.gyh.part2.day50;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

public class Solution2 {
    public static void main(String[] args) throws Exception {
        // 读取配置，创建数据库连接池
        Properties info = new Properties();
        Reader reader = new FileReader("druid.properties");
        info.load(reader);
        reader.close();
        DataSource ds = DruidDataSourceFactory.createDataSource(info);

        // 获取连接
        Connection conn = ds.getConnection();

        // 创建 user 表
        Statement stmt = conn.createStatement();
        String sql = "create table user(id int primary key auto_increment, " +
                "username varchar(255) not null" +
                "password varchar(255) not null, " +
                "balance decimal(18,2) default 0)";
        stmt.execute(sql);

        // 插入 10000 条数据
        sql = "insert into user(username, password, balance) values(?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            pstmt.setString(1, "user" + i);
            pstmt.setString(2, "pwd" + i);
            pstmt.setBigDecimal(3, new BigDecimal(r.nextInt(15000)));
            pstmt.addBatch();
        }
        pstmt.executeBatch();

        // 查询 balance>10000 的记录，并写入到 test.txt 文件中
        sql = "select username, balance from user where balance > 10000";
        ResultSet rs = stmt.executeQuery(sql);
        PrintWriter writer = new PrintWriter(new FileWriter("test.txt"));
        writer.println("username" + "\t" + "balance");
        while (rs.next()) {
            String username = rs.getString("username");
            BigDecimal balance = rs.getBigDecimal("balance");
            writer.println(username + "\t" + balance);
        }
        writer.close();
        conn.close();
    }
}
