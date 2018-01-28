package com.dao;

import java.sql.Connection;

public interface DAO <T> {
    void buy(T type, Connection connection);
    void listAll(T type, Connection connection);
    void showOne(T type, Connection connection);
    void remove(T type, Connection connection);
}
