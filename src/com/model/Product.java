package com.model;

public class Product {
    private int productId;
    private String productName;
    private String tag;
    private float price;
    private int stock;

    public Product(String productName, String tag, float price, int stock) {
        this.productName = productName;
        this.tag = tag;
        this.price = price;
        this.stock = stock;
    }


}
