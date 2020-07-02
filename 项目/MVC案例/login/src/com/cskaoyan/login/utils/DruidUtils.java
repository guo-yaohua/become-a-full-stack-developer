package com.cskaoyan.login.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {

    private static DataSource dataSource;

    static {
        //Druid 符合java数据库连接的规范的 所以肯定有一个datasource
        //如果放在WEB-INF下，必须要提供context或者相关
        //但是如果这么做，工具类就无法在se项目中使用
        //可以利用类加载器去获取一个绝对路径
        //类加载器有一个API可以直接定位到classes目录
        try {
            //要求就是文件必须放置在src目录下
            InputStream inputStream = DruidUtils.class.getClassLoader().
                    getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    /**
     *  Class.forName("com.mysql.jdbc.Driver");
     *  connection = DriverManager.getConnection("", "", "");
     *  今后获取连接使用下面代码即可，不要自己去getConnection
     *  因为上面没有数据库连接池
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
