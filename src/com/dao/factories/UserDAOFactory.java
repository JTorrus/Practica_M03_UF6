package com.dao.factories;

import com.dao.CustomerDAO;
import com.dao.sql_dao.CustomerDAOJDBCImpl;

public class UserDAOFactory {
    public CustomerDAO createUserDAO() {
        return new CustomerDAOJDBCImpl();
    }
}
