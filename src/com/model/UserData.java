package com.model;

public class UserData {
    private String city;
    private String postalCode;
    private String email;
    private float money;

    public UserData(String city, String postalCode, String email, float money) {
        this.city = city;
        this.postalCode = postalCode;
        this.email = email;
        this.money = money;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getEmail() {
        return email;
    }

    public float getMoney() {
        return money;
    }
}
