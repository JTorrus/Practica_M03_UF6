package com.dao.sql_dao;

import com.dao.ProductDAO;
import com.model.Audio;
import com.model.Product;
import com.model.Smartphone;

import java.sql.*;

public class ProductDAOJDBCImpl implements ProductDAO {

    private final String UPDATE_BUY_PRODUCT = "UPDATE product SET stock = stock - ? WHERE product_name = ?";
    private final String QUERY_GET_ALL_PRODUCTS = "SELECT * FROM product, smartphone, audio where product_id = smartphone_id OR product_id = audio_id";
    private final String QUERY_GET_ALL_SMARTPHONES = "SELECT p.product_id, p.name, p.brand, s.os, s.internal_storage, s.color, s.is_unlocked, p.price, p.stock FROM product AS p, smartphone AS s WHERE s.smartphone_id = p.product_id";
    private final String QUERY_GET_ALL_AUDIO = "SELECT p.product_id, p.name, p.brand, a.type, a.sound_mode, a.is_wireless, a.has_microphone, p.price, p.stock FROM product AS p, audio AS a WHERE a.audio_id = p.product_id";
    private final String QUERY_GET_ONE_PRODUCT = "SELECT * FROM product WHERE product_id = ?";




    @Override
    public void listAll(int choice, Connection connection) {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            if (choice == 1) {
                stmt = connection.createStatement();
                rs = stmt.executeQuery(QUERY_GET_ALL_SMARTPHONES);
                showProduct(1, rs);
            } else if (choice == 2) {
                stmt = connection.createStatement();
                rs = stmt.executeQuery(QUERY_GET_ALL_AUDIO);
                showProduct(2, rs);
            } else {
                stmt = connection.createStatement();
                rs = stmt.executeQuery(QUERY_GET_ALL_PRODUCTS);
                showProduct(3, rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
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
            showProduct(3, rs);
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
            if (choice == 1) {
                String name = rs.getString("name");
                int product_id = rs.getInt("product_id");
                String brand = rs.getString("brand");
                float price = rs.getFloat("price");
                int stock = rs.getInt("stock");
                String os = rs.getString("os");
                int internalStorage = rs.getInt("internal_storage");
                String color = rs.getString("color");
                boolean isUnlocked = rs.getBoolean("is_unlocked");

                System.out.println(new Smartphone(product_id, name, brand, price, stock, os, internalStorage, color, isUnlocked));
            }
            else if (choice == 2) {
                String name = rs.getString("name");
                int product_id = rs.getInt("product_id");
                String brand = rs.getString("brand");
                float price = rs.getFloat("price");
                int stock = rs.getInt("stock");
                String type = rs.getString("type");
                String soundMode = rs.getString("sound_mode");
                boolean wireless = rs.getBoolean("is_wireless");
                boolean microphone = rs.getBoolean("has_microphone");

                System.out.println(new Audio(product_id, name, brand, price, stock, type, soundMode, wireless, microphone));
            }
        }
    }

    @Override
    public void buy(int qty, String name, Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_BUY_PRODUCT);
            preparedStatement.setInt(1,qty);
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Database Error::"+e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.out.println("Database Error::"+e.getMessage());
            }
        }finally{
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Database Error::"+e.getMessage());
                }
            }
        }
    }
}
