package com.dao;

import com.model.UserSignIn;

import java.sql.Connection;

public interface UserSigninDAO {
    int getUserId(UserSignIn us, Connection connection);
    float checkMyWallet(UserSignIn us, Connection connection);
    void addFunds(Double funds, UserSignIn us, Connection connection);
}
