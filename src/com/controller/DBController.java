package com.controller;

import com.model.UserProfile;
import com.model.UserSignIn;

import java.sql.*;
import java.util.ArrayList;

public class DBController {

    private final String INSERT_ADD_USERDATA = "INSERT INTO usersignin(username, pass) VALUES(?, ?)";
    private final String INSERT_ADD_USERPROFILE = "INSERT INTO userprofile(location, zipcode, email, funds) VALUES(?, ?, ?, ?)";
    private final String QUERY_GET_USER_ID = "SELECT us.user_id FROM usersignin AS us WHERE us.username = ? AND us.pass = ? ";
    private final String QUERY_GET_USERPROFILE_ID = "SELECT up.profile_id FROM userprofile AS up WHERE up.location = ? AND up.zipcode = ? AND up.email = ? AND up.funds = ? ";
    private final String CREATE_IDS_RELATION = "INSERT into usersignin_userprofile values (?, ?)";
    private final String DELETE_REMOVE_USERSIGNIN = "DELETE FROM usersignin WHERE user_id = ?";

    //Method that allow us to create users
    public void registerUer(UserSignIn userSignIn, UserProfile userProfile, Connection connection) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADD_USERDATA); PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_ADD_USERPROFILE)) {

            connection.setAutoCommit(false);

            preparedStatement.setString(1, userSignIn.getUsername());
            preparedStatement.setString(2, userSignIn.getPassword());

            preparedStatement2.setString(1, userProfile.getCity());
            preparedStatement2.setString(2, userProfile.getZipCode());
            preparedStatement2.setString(3, userProfile.getEmail());
            preparedStatement2.setDouble(4, userProfile.getMoney());


            preparedStatement2.executeUpdate();
            preparedStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            System.out.println("Database error::" + e.getMessage());
            connection.rollback();
        }
    }


    //    Method that return user and user_profile ID
    private ArrayList<Integer> getUserIds(UserSignIn userSignIn, UserProfile userProfile, Connection connection) {
        ArrayList<Integer> ids = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = connection.prepareStatement(QUERY_GET_USER_ID);
            stmt.setString(1, userSignIn.getUsername());
            stmt.setString(2, userSignIn.getPassword());

            rs = stmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt(1));
            }


            stmt = connection.prepareStatement(QUERY_GET_USERPROFILE_ID);
            stmt.setString(1, userProfile.getCity());
            stmt.setString(2, userProfile.getZipCode());
            stmt.setString(3, userProfile.getEmail());
            stmt.setDouble(4, userProfile.getMoney());

            rs = stmt.executeQuery();

            while (rs.next()) {
                ids.add(rs.getInt(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Database Error::" + e.getMessage());
            }
        }

        System.out.println(ids);
        return ids;
    }


    //Method for removing users
    public void removeUser(UserSignIn us, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REMOVE_USERSIGNIN)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, us.getUserId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Database error");
            connection.rollback();
        }
    }

    //Method that return true if user exists (For login)
    public boolean checkUser(UserSignIn userSignIn, Connection connection) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean exist = false;

        try {
            stmt = connection.prepareStatement(QUERY_GET_USER_ID);
            stmt.setString(1, userSignIn.getUsername());
            stmt.setString(2, userSignIn.getPassword());

            rs = stmt.executeQuery();

            if (rs.next()) {
                exist = true;
            }

        } catch (SQLException e) {
            System.out.println("Database Error::" + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.out.println("Database Error::" + e.getMessage());
            }
        }
        return exist;

    }
}
