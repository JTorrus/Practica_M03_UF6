package com.dao;

import com.model.User;

import java.sql.Connection;

public interface UserDAO {
    void addUser(User user, Connection connection);
    void removeUser(User user, Connection connection);
}
