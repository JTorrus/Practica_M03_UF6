package com.dao;

import java.sql.Connection;

public interface DAO <T> {
    void listAll(Connection connection);
    void showOne(int id, Connection connection);
    void remove(int id, Connection connection);
    void add(T type, Connection connection);
}
