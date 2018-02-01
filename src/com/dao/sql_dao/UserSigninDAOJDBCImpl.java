package com.dao.sql_dao;

import com.controller.DBController;
import com.dao.UserSigninDAO;
import com.model.UserSignIn;

import java.sql.*;

public class UserSigninDAOJDBCImpl implements UserSigninDAO {

    private final String QUERY_CHECK_WALLET = "SELECT funds FROM userprofile up WHERE up.profile_id = ?";
    private final String UPDATE_ADD_FUNDS = "UPDATE userprofile SET funds = funds + ? WHERE profile_id = ?";


    @Override
    public float checkMyWallet(UserSignIn us, Connection connection) {
        float funds = 0.0f;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(QUERY_CHECK_WALLET);
            ps.setInt(1, DBController.getUserId(us, connection));
            rs = ps.executeQuery();
            while (rs.next()) {
                funds = rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.out.println("Database ERROR");
                }
            }if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.out.println("Database ERROR");
                }
            }
        }
        return funds;
    }

    @Override
    public void addFunds(float funds, UserSignIn us, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADD_FUNDS)) {
            connection.setAutoCommit(false);
            preparedStatement.setFloat(1, funds);
            preparedStatement.setInt(2, DBController.getUserId(us, connection));
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }


}
