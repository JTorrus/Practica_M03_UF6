package com.dao.sql_dao;

import com.dao.UserSigninDAO;
import com.model.UserSignIn;

import java.sql.*;

public class UserSigninDAOJDBCImpl implements UserSigninDAO {
    private final String QUERY_ADD_USERDATA = "INSERT INTO usersignin(username, pass) VALUES(?, ?)";
    private final String QUERY_ADD_USERPROFILE = "INSERT INTO userprofile(location, zipcode, email, funds) VALUES(?, ?, ?, ?)";
    private final String QUERY_REMOVE_USERPROFILE = "DELETE FROM userprofile WHERE profile_id = ?";
    private final String QUERY_GET_HIGER_ID = "SELECT max(profile_id) FROM userprofile";
    private final String QUERY_CHECK_WALLET = "SELECT funds FROM userprofile up WHERE up.profile_id = " +
            "(SELECT profile_id FROM usersignin_userprofile WHERE user_id = ?)";

    @Override
    public void register(UserSignIn userSignIn, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_USERDATA)) {

            preparedStatement.setString(1, userSignIn.getUsername());
            preparedStatement.setString(2, userSignIn.getPassword());

            preparedStatement.executeUpdate();

            System.out.println(userSignIn.getUsername());
            System.out.println(userSignIn.getPassword());
        } catch (SQLException e) {
            System.out.println("Database error::" + e.getMessage());
        }
    }

    @Override
    public void removeUser(UserSignIn us, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_USERPROFILE)) {
            preparedStatement.setInt(1, us.getProfileId());
        } catch (SQLException e) {
            System.out.println("Database error");
        }
    }

    @Override
    public float checkMyWallet(UserSignIn us, Connection connection) {
        float funds = 0.0f;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(QUERY_CHECK_WALLET);
            ps.setInt(1, us.getProfileId());
            rs = ps.executeQuery();
            funds = rs.getFloat(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (ps!=null){
                try {
                    ps.close();
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Database ERROR");;
                }
            }
        }
        return funds;
    }


}
