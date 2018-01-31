package com.dao.sql_dao;

import com.dao.ProductDAO;
import com.model.Product;

import java.sql.*;

public class ProductDAOJDBCImpl implements ProductDAO {

    private final String REMOVE_PRODUCT = "DELETE FROM product WHERE product_id = ?";
    private final String QUERY_GET_ALL_PRODUCTS = "SELECT * FROM product";
    private final String QUERY_GET_ALL_SMARTPHONES = "SELECT p.name, p.brand, s.os, s.internal_storage, s.color, s.is_unlocked, p.price, p.stock FROM product AS p, smartphone AS s WHERE s.smartphone_id = p.product_id";
    private final String QUERY_GET_ALL_AUDIO = "SELECT p.name, p.brand, a.type, a.sound_mode, a.is_wireless, a.has_microphone, p.price, p.stock FROM product AS p, audio AS a WHERE a.audio_id = p.product_id";
    private final String QUERY_GET_ONE_PRODUCT = "SELECT * FROM product WHERE product_id = ?";


    @Override
    public void buy(Product product, Connection connection) {

    }

    @Override
    public void listAll(int choice, Connection connection) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            if (choice==1){
                stmt = connection.createStatement(); rs = stmt.executeQuery(QUERY_GET_ALL_SMARTPHONES);
                showProduct(rs);
            }else if (choice==2){
                stmt = connection.createStatement(); rs = stmt.executeQuery(QUERY_GET_ALL_AUDIO);
                showProduct(rs);
            }else{
                stmt = connection.createStatement(); rs = stmt.executeQuery(QUERY_GET_ALL_PRODUCTS);
                showProduct(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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

    private void showProduct(int choice, ResultSet rs) throws SQLException {
        while (rs.next()) {
            if (choice==1){
                String name = rs.getString("name");
                String brand = rs.getString("brand");
                float price = rs.getFloat("price");
                int stock = rs.getInt("stock");
                String os = rs.getString("os");
                int internalStorage = rs.getInt("internal_storage");
            }
            int product_id = rs.getInt("product_id");


            System.out.println(new Product(product_id, name, brand, price, stock) {
            });
        }
    }
}
