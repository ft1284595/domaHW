package com.servlet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class DBConnectionManager {
    private Connection connection;

    public DBConnectionManager(String dbURL, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection(dbURL+"?serverTimezone=" + TimeZone.getDefault().getID(), user, pwd);
    }

    public Connection getConnection(){
        return this.connection;
    }
}
