package com.dao.sql_dao;

import com.dao.OrderDAO;
import com.model.ProductOrder;

import java.sql.*;

public class OrderDAOJDBCImpl implements OrderDAO {

    private final String REMOVE_BILL = "DELETE FROM bill WHERE bill_id = ?";
    private final String QUERY_GET_ALL_BILLS = "SELECT * FROM bill WHERE ";
    private final String QUERY_GET_ONE_BILL = "SELECT * FROM bill WHERE bill_id = ?";


    @Override
    public void listAll(int choice, Connection connection) {
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(QUERY_GET_ALL_BILLS)) {
            showOrder(rs);
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
            showOrder(rs);
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
    public void add(ProductOrder productOrder, Connection connection) {

    }

    private void showOrder(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int order_id = rs.getInt("order_id");
            Date bill_date = rs.getDate("bill_date");
            double final_price = rs.getDouble("final_price");

            System.out.println(new ProductOrder(order_id, userId, productId, orderDate, final_price));
        }
    }
}
