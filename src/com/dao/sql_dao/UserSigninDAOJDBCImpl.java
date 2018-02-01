package com.dao.sql_dao;

import com.dao.UserSigninDAO;
import com.model.UserSignIn;

import java.sql.*;

public class UserSigninDAOJDBCImpl implements UserSigninDAO {

    private final String QUERY_CHECK_WALLET = "SELECT funds FROM userprofile up WHERE up.profile_id = ?";
    private final String UPDATE_ADD_FUNDS = "UPDATE userprofile SET funds = funds + ? WHERE profile_id = ?";
    private final String QUERY_GET_USER_ID = "SELECT us.user_id FROM usersignin AS us WHERE us.username = ? AND us.pass = ? ";



    //    Method that return user and user_profile ID
    public int getUserId(UserSignIn userSignIn, Connection connection) {
        int id = -1;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {

            stmt = connection.prepareStatement(QUERY_GET_USER_ID);
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


    @Override
    public float checkMyWallet(UserSignIn us, Connection connection) {
        float funds = 0.0f;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(QUERY_CHECK_WALLET);
            ps.setInt(1, getUserId(us,connection));
            rs = ps.executeQuery();
            while (rs.next()){
                funds = rs.getFloat(1);
            }
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

    @Override
    public void addFunds(Double funds, UserSignIn us, Connection connection) {

    }


}
