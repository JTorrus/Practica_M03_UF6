package com.model;

public class UserSignIn {
    private int userId;
    private String username;
    private String password;
    private UserProfile userProfile;

    public UserSignIn(String username, String password, UserProfile userProfile) {
        this.username = username;
        this.password = password;
        this.userProfile = userProfile;
    }
}
