package com.dao;

import com.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOJDBCImpl implements UserDAO{
    private final String QUERY_ADD_USERDATA = "INSERT INTO usersignin(username, pass) VALUES(?, ?)";
    private final String QUERY_ADD_USERPROFILE = "INSERT INTO userprofile(location, zipcode, email, funds) VALUES(?, ?, ?, ?)";
    private final String QUERY_REMOVE_USERDATA = "DELETE FROM usersignin WHERE username = ? AND pass = ?";
    private final String QUERY_REMOVE_USERPROFILE = "DELETE FROM userprofile WHERE ";

    @Override
    public void addUser(User user, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_USERDATA)){

            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());

            preparedStatement.executeUpdate();

            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
        }catch(SQLException e){
            System.out.println("Database error::"+e.getMessage());
        }
    }

    @Override
    public void removeUser(User user, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_USERDATA)){
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
        }catch (SQLException e){
            System.out.println("Database error");
        }
    }
}
