package com.model;

import sun.java2d.cmm.Profile;

public class User {
    private String username;
    private String password;
    private UserData userData;

    public User(String username, String password, UserData userData) {
        this.username = username;
        this.password = password;
        this.userData = userData;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserData getUserData() {
        return userData;
    }
}
