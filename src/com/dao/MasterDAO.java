package com.dao;

import com.model.Master;
import com.model.Product;
import com.model.UserSignIn;

import java.sql.Connection;

public interface MasterDAO extends DAO<Master> {
    void add(Product prod, Connection connection);
}
