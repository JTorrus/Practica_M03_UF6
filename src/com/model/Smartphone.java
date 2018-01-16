package com.model;

public class Smartphone {

    private int smartphoneId;
    private String operatingSystem;
    private int storage;
    private boolean free;

    public Smartphone(String operatingSystem, int storage, boolean free) {
        this.operatingSystem = operatingSystem;
        this.storage = storage;
        this.free = free;
    }
}
