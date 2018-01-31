package com.dao;

import com.model.UserSignIn;

import java.sql.Connection;

public interface UserSigninDAO {
    float checkMyWallet(UserSignIn us, Connection connection);
}
