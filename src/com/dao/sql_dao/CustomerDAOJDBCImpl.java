package com.dao.sql_dao;

import com.model.UserSignIn;

import java.sql.*;

public class CustomerDAOJDBCImpl implements CustomerDAO {


    @Override
    public void removeOrBuy(UserSignIn userSignIn, Connection connection) {

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
