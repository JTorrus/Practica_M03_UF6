package com.controller;

import com.jdbc_utilities.DBConnection;
import com.model.UserProfile;
import com.model.UserSignIn;

import java.sql.*;

public class DBController {

    private final String INSERT_ADD_USERDATA = "INSERT INTO usersignin(username, pass) VALUES(?, ?)";
    private final String INSERT_ADD_USERPROFILE = "INSERT INTO userprofile(profile_id, location, zipcode, email, funds) VALUES(?, ?, ?, ?, ?)";
    private static final String QUERY_CHECK_LOGIN = "SELECT us.user_id FROM usersignin AS us WHERE us.username = ? AND us.pass = ? ";
    private static final String QUERY_CHECK_USER_EXISTS = "SELECT us.user_id FROM usersignin AS us, userprofile as up WHERE us.username = ? OR up.email = ? AND us.user_id = up.profile_id";
    private final String DELETE_REMOVE_USERSIGNIN = "DELETE FROM usersignin WHERE user_id = ?";

    //    Method that return user ID
    public static int getUserId(UserSignIn userSignIn, Connection connection) {
        int id = -1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = connection.prepareStatement(QUERY_CHECK_LOGIN);
            stmt.setString(1, userSignIn.getUsername());
            stmt.setString(2, userSignIn.getPassword());

            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1);
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

        return id;
    }

    //Method that allow us to create users
    public boolean registerUser(UserSignIn userSignIn, UserProfile userProfile, Connection connection) throws SQLException {
        if (checkUserExists(userSignIn, userProfile, DBConnection.getInstance())){
            System.out.println("Sorry this user already exists");
            return false;
        }else{
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADD_USERDATA); PreparedStatement preparedStatement2 = connection.prepareStatement(INSERT_ADD_USERPROFILE)) {

                connection.setAutoCommit(false);

                preparedStatement.setString(1, userSignIn.getUsername());
                preparedStatement.setString(2, userSignIn.getPassword());


                preparedStatement.executeUpdate();

                connection.commit();

                preparedStatement2.setInt(1, getUserId(userSignIn,connection));
                preparedStatement2.setString(2, userProfile.getCity());
                preparedStatement2.setString(3, userProfile.getZipCode());
                preparedStatement2.setString(4, userProfile.getEmail());
                preparedStatement2.setDouble(5, userProfile.getMoney());

                preparedStatement2.executeUpdate();


                connection.commit();

            } catch (SQLException e) {
                System.out.println("Database error::" + e.getMessage());
                connection.rollback();
            }
        }
        return true;
    }


    //Method for removing users
    public void removeUser(UserSignIn us, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REMOVE_USERSIGNIN)) {
            connection.setAutoCommit(false);
            preparedStatement.setInt(1, getUserId(us, DBConnection.getInstance()));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Database error");
            connection.rollback();
        }
    }

    //Method that return true if user exists (For login & register)
    public boolean checkUserAndPassword(UserSignIn userSignIn, Connection connection) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean exist = false;

        try {
            stmt = connection.prepareStatement(QUERY_CHECK_LOGIN);
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

    private boolean checkUserExists(UserSignIn userSignIn, UserProfile userProfile, Connection connection) {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean exist = false;

        try {
            stmt = connection.prepareStatement(QUERY_CHECK_USER_EXISTS);
            stmt.setString(1, userSignIn.getUsername());
            stmt.setString(2, userProfile.getEmail());

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
