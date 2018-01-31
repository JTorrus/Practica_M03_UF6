package com.model;

public class UserProfile {
    private int profileId;
    private String city;
    private String zipCode;
    private String email;
    private Double money;

    public UserProfile(String city, String zipCode, String email, Double money) {
        this.city = city;
        this.zipCode = zipCode;
        this.email = email;
        this.money = money;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }
}
