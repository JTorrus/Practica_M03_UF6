package com.dao.sql_dao;

import com.dao.OrderDAO;
import com.model.ProductOrder;

import java.sql.*;

public class OrderDAOJDBCImpl implements OrderDAO {

    private static final String QUERY_GET_ALL_ORDERS = "SELECT p.name, po.order_date, po.final_price FROM productorder AS" +
            " po, usersignin AS us, product AS p WHERE po.user_id = us.user_id AND po.product_id = p.product_id AND po.user_id = ?";
    private static final String INSERT_ADD_ORDER = "INSERT INTO productorder (user_id, product_id, order_date, final_price) VALUES (?, ?, ?, ?)";


    @Override
    public void listAll(int id, Connection connection) {
        ResultSet rs = null;
        try (PreparedStatement stmt = connection.prepareStatement(QUERY_GET_ALL_ORDERS)) {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String productName = rs.getString("name");
                Timestamp orderDate = rs.getTimestamp("order_date");
                float finalPrice = rs.getFloat("final_price");

                System.out.println("Product: " + productName + ", Order_Date: " + orderDate + ", Final Price: " + finalPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void add(ProductOrder productOrder, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADD_ORDER)) {
            connection.setAutoCommit(false);
            System.out.println(productOrder.getUserId());
            System.out.println(productOrder.getProductId());
            System.out.println(productOrder.getOrderDate());
            System.out.println(productOrder.getFinalPrice());
            preparedStatement.setInt(1, productOrder.getUserId());
            preparedStatement.setInt(2, productOrder.getProductId());
            preparedStatement.setTimestamp(3, productOrder.getOrderDate());
            preparedStatement.setFloat(4, productOrder.getFinalPrice());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Database Error::" + e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("Insert Error::" + e1.getMessage());
            }
        }
    }
}
