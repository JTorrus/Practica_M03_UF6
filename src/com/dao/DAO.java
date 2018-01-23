package com.dao;

import java.sql.Connection;

public interface DAO <T> {
    void removeOrBuy(T type, Connection connection);
    void listAll(T type, Connection connection);
    void showOne(T type, Connection connection);
}
