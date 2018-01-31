package com.dao;

import com.model.Product;

import java.sql.Connection;

public interface ProductDAO extends DAO<Product> {
    void buy (int qty, String name, Connection connection);
}
