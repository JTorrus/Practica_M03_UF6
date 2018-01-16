package com.dao;

public class UserDAOFactory {
    public UserDAO createUserDAO() {
        return new UserDAOJDBCImpl();
    }
}
