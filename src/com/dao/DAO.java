package com.dao;

import java.sql.Connection;

public interface DAO <T> {
    void listAll(int choice, Connection connection);
    void add(T type, Connection connection);
}
