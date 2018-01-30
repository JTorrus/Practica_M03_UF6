package com.dao.sql_dao;

import com.dao.ProductDAO;
import com.model.Product;

import java.sql.*;

public class ProductDAOJDBCImpl implements ProductDAO {

    private final String REMOVE_PRODUCT = "DELETE FROM product WHERE product_id = ?";
    private final String QUERY_GET_ALL_PRODUCTS = "SELECT * FROM product";
    private final String QUERY_GET_ONE_PRODUCT = "SELECT * FROM product WHERE product_id = ?";


    @Override
    public void buy(Product product, Connection connection) {

    }

    @Override
    public void listAll(Connection connection) {
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(QUERY_GET_ALL_PRODUCTS)) {
            showProduct(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showOne(int id, Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(QUERY_GET_ONE_PRODUCT);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            showProduct(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_PRODUCT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Database error");
        }
    }

    @Override
    public void add(Product product, Connection connection) {

    }

    private void showProduct(ResultSet rs) throws SQLException {
        while (rs.next()) {
            int product_id = rs.getInt("product_id");
            String name = rs.getString("name");
            String brand = rs.getString("brand");
            float price = rs.getFloat("price");
            int stock = rs.getInt("stock");

            System.out.println(new Product(product_id, name, brand, price, stock) {
            });
        }
    }
}
