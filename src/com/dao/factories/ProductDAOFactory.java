package com.dao.factories;

public class ProductDAOFactory {
    public ProductDAO createProductDAO() {
        return new ProductDAOJDBCImpl();
    }
}
