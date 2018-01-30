package com.dao;

import com.model.Product;

import java.sql.Connection;

public interface ProductDAO extends DAO<Product> {
    void buy (Product product, Connection connection);
}
