package com.dao.sql_dao;

import com.dao.CustomerDAO;
import com.model.UserSignIn;

import java.sql.*;

public class CustomerDAOJDBCImpl implements CustomerDAO {
    private final String QUERY_ADD_USERDATA = "INSERT INTO usersignin(username, pass) VALUES(?, ?)";
    private final String QUERY_ADD_USERPROFILE = "INSERT INTO userprofile(location, zipcode, email, funds) VALUES(?, ?, ?, ?)";
    private final String QUERY_REMOVE_USERPROFILE = "DELETE FROM userprofile WHERE profile_id = ?";

    @Override
    public void add(UserSignIn userSignIn, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ADD_USERDATA)){

            preparedStatement.setString(1, userSignIn.getUsername());
            preparedStatement.setString(2, userSignIn.getPassword());

            preparedStatement.executeUpdate();

            System.out.println(userSignIn.getUsername());
            System.out.println(userSignIn.getPassword());
        }catch(SQLException e){
            System.out.println("Database error::"+e.getMessage());
        }
    }

    @Override
    public void removeOrBuy(UserSignIn userSignIn, Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_REMOVE_USERPROFILE)){
            preparedStatement.setString(1, "abc");
        }catch (SQLException e){
            System.out.println("Database error");
        }
    }

    @Override
    public void listAll(UserSignIn type, Connection connection) {

    }

    @Override
    public void showOne(UserSignIn type, Connection connection) {

    }

    @Override
    public void checkMyWallet(UserSignIn us, Connection connection) {

    }

    @Override
    public void checkMyBills(UserSignIn us, Connection connection) {

    }

    //TODO PASAR UN ID DE PERFIL MEDIANTE EL SIGNIN (NECESITAMOS ELIMINAR EL ID DE USERPROFILE PARA QUE FUNCIONE EL ON CASCADE)
}
