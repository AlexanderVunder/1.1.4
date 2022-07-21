package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection(String url, String user, String password) throws SQLException {
        Connection connection =DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        return connection;
    }
    // реализуйте настройку соеденения с БД
}
