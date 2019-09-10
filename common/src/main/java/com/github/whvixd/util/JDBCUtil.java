package com.github.whvixd.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Created by wangzhx on 2018/4/17.
 */
public class JDBCUtil {
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/lanqiao";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    private static ThreadLocal<Connection> container = new ThreadLocal<>();

    public static Connection getConnection() {
        try {
            Connection connection = container.get();
            if (Objects.isNull(connection)) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
//              connection.setAutoCommit(false);//开启事务
                container.set(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return container.get();
        }
    }

    public static Connection getConnection(String url, String user, String password) {
        Connection connection = container.get();
        try {
            if (Objects.isNull(connection)) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(url, user, password);
                container.set(connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return container.get();
        }
    }

    public static void closeConnection() {
        Connection connection = container.get();
        try {
            if (Objects.nonNull(connection)) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            container.remove();
        }
    }
}
