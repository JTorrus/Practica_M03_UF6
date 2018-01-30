package com.dao.sql_dao;

import com.dao.BillDAO;
import com.model.Bill;

import java.sql.*;

public class BillDAOJDBCImpl implements BillDAO {

    private final String REMOVE_BILL = "DELETE FROM bill WHERE bill_id = ?";
    private final String QUERY_GET_ALL_BILLS = "SELECT * FROM bill";
    private final String QUERY_GET_ONE_BILL = "SELECT * FROM bill WHERE bill_id = ?";


    @Override
    public void listAll(Connection connection) {
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(QUERY_GET_ALL_BILLS)) {
            showBill(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showOne(int id, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_GET_ONE_BILL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            showBill(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void remove(int id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_BILL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error");
        }
    }

    @Override
    public void add(Bill bill, Connection connection) {

    }

    private void showBill(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int bill_id = rs.getInt("bill_id");
            int order_id = rs.getInt("order_id");
            Date bill_date = rs.getDate("bill_date");
            double final_price = rs.getDouble("final_price");

            System.out.println(new Bill(bill_id, order_id, bill_date, final_price));
        }
    }
}
