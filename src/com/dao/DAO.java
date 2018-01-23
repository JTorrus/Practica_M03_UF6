package com.dao;

import java.sql.Connection;

public interface DAO <T> {
    void add(T type, Connection connection);
    void remove(T type, Connection connection);
}
