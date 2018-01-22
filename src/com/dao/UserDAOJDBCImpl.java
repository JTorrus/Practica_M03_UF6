package com.dao;

import com.model.UserSignIn;

import java.sql.*;

public class UserDAOJDBCImpl implements UserDAO{
    private final String QUERY_ADD_USERDATA = "INSERT INTO usersignin(username, pass) VALUES(?, ?)";
    private final String QUERY_ADD_USERPROFILE = "INSERT INTO userprofile(location, zipcode, email, funds) VALUES(?, ?, ?, ?)";
    private final String QUERY_REMOVE_USERPROFILE = "DELETE FROM userprofile WHERE profile_id = ?";
    private final String QUERY_GET_ID_FROM_USER = "SELECT"

    @Override
    public void addUser(UserSignIn userSignIn, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_USERDATA)){

            preparedStatement.setString(1, userSignIn.getUsername());
            preparedStatement.setString(2, userSignIn.getPassword());

            preparedStatement.executeUpdate();

            System.out.println(userSignIn.getUsername());
            System.out.println(userSignIn.getPassword());
        }catch(SQLException e){
            System.out.println("Database error::"+e.getMessage());
        }
    }

    @Override
    public void removeUser(UserSignIn userSignIn, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_USERPROFILE)){
            preparedStatement.setString(1, userSignIn.);
        }catch (SQLException e){
            System.out.println("Database error");
        }
    }

    public int getIdFromUser(UserSignIn userSignIn, Connection connection) {
        try (Statement statement = connection.createStatement()) {

        }
    }
}
