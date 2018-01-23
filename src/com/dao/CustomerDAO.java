package com.dao;

import com.model.UserProfile;
import com.model.UserSignIn;

import java.sql.Connection;

public interface CustomerDAO extends DAO<UserSignIn> {
    void checkMyWallet(UserSignIn us, Connection connection);
    void checkMyBills(UserSignIn us, Connection connection);
}
