package com.jdbc_utilities;

import com.controller.DBUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection conn;
    private DBConnection() {}

    public static Connection getInstance() throws SQLException{
        if (conn==null){
            conn = DriverManager.getConnection(DBUtilities.URL, DBUtilities.USERNAME, DBUtilities.PASSWORD);
        }
        return conn;
    }

    public static void disconnect(){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Connection error::"+e.getMessage());
            }
        }
    }

}
