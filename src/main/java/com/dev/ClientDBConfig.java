package com.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClientDBConfig {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/school";
    private static String user = "root";
    private static String password = "rootroot";

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        return DriverManager.getConnection(url, user,
                password);
    }
}
