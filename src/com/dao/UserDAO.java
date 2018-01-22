package com.dao;

import com.model.UserSignIn;

import java.sql.Connection;

public interface UserDAO {
    void addUser(UserSignIn userSignIn, Connection connection);
    void removeUser(UserSignIn userSignIn, Connection connection);
}
