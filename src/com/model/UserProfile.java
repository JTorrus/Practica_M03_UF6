package com.model;

public class UserProfile {
    private int profileId;
    private String city;
    private String postalCode;
    private String email;
    private float money;

    public UserProfile(int profileId, String city, String postalCode, String email, float money) {
        this.profileId = profileId;
        this.city = city;
        this.postalCode = postalCode;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }
}
