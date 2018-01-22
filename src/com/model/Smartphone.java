package com.model;

public class Smartphone extends Product {
    private String os;
    private int internalStorage;
    private String color;
    private boolean unlocked;

    public Smartphone(int smartphoneId, String name, String brand, float price, int stock, String os, int internalStorage, String color, boolean unlocked) {
        super(smartphoneId, name, brand, price, stock);
        this.os = os;
        this.internalStorage = internalStorage;
        this.color = color;
        this.unlocked = unlocked;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public int getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(int internalStorage) {
        this.internalStorage = internalStorage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }
}
