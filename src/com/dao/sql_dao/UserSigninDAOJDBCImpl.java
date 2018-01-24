package com.dao.sql_dao;

import com.dao.UserSigninDAO;
import com.model.UserSignIn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UserSigninDAOJDBCImpl implements UserSigninDAO {
    private final String QUERY_ADD_USERDATA = "INSERT INTO usersignin(username, pass) VALUES(?, ?)";
    private final String QUERY_ADD_USERPROFILE = "INSERT INTO userprofile(location, zipcode, email, funds) VALUES(?, ?, ?, ?)";
    private final String QUERY_REMOVE_USERPROFILE = "DELETE FROM userprofile WHERE profile_id = ?";
    private final String QUERY_GET_HIGER_ID = "SELECT max(profile_id) FROM userprofile";

    @Override
    public void register(UserSignIn userSignIn, Connection connection) {
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
    public void removeUser(UserSignIn us, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_USERPROFILE)){
            preparedStatement.setString(1, "abc");
        }catch (SQLException e){
            System.out.println("Database error");
        }
    }

    @Override
    public int getLastUserID(Connection connection) {
        try (Statement statement = connection.createStatement(QUERY_GET_HIGER_ID)){
        }catch (SQLException e){}
    }

}