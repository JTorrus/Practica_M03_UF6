package com.dao;

import com.model.UserSignIn;

import java.sql.Connection;

public interface UserSigninDAO {
    void register(UserSignIn us, Connection connection);
    void removeUser(UserSignIn us, Connection connection);
    int getLastUserID (Connection connection);
    void checkMyWallet(UserSignIn us, Connection connection);
    void checkMyBills(UserSignIn us, Connection connection);
}
