package com.dao.sql_dao;

import com.dao.OrderDAO;
import com.model.ProductOrder;

import java.sql.*;

public class OrderDAOJDBCImpl implements OrderDAO {

    private final String DELETE_REMOVE_ORDER = "DELETE FROM productorder WHERE order_id = ?";
    private final String QUERY_GET_ALL_ORDERS = "SELECT * FROM productorder WHERE user_id = ?";
    private final String QUERY_GET_ONE_ORDER = "SELECT * FROM productorder WHERE order_id = ?";


    @Override
    public void listAll(int choice, Connection connection) {
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(QUERY_GET_ALL_ORDERS)) {
            showOrder(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showOne(int id, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_GET_ONE_ORDER);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            showOrder(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void add(ProductOrder productOrder, Connection connection) {

    }

    private void showOrder(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int orderId = rs.getInt("order_id");
            int userId = rs.getInt("user_id");
            int productId = rs.getInt("product_id");
            Timestamp orderDate = rs.getTimestamp("order_date");
            Date bill_date = rs.getDate("bill_date");
            float final_price = rs.getFloat("final_price");

            System.out.println(new ProductOrder(orderId, userId, productId, orderDate, final_price));
        }
    }
}
