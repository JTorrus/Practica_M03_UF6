package com.jdbc_utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn;
    private DBConnection() {}

    public static Connection getInstance() throws SQLException{
        if (conn==null){
            conn = DriverManager.getConnection(DBUtilities.URL, DBUtilities.USERNAME, DBUtilities.PASSWORD);
            System.out.println("Connected");
        }
        return conn;
    }

}
