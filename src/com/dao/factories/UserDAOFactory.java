package com.dao.factories;

import com.dao.UserDAO;
import com.dao.sql_dao.UserDAOJDBCImpl;

public class UserDAOFactory {
    public UserDAO createUserDAO() {
        return new UserDAOJDBCImpl();
    }
}
