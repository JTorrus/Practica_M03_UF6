package com.dao.factories;

import com.dao.ProductDAO;
import com.dao.sql_dao.ProductDAOJDBCImpl;

public class ProductDAOFactory {
    public ProductDAO createProductDAO() {
        return new ProductDAOJDBCImpl();
    }
}
