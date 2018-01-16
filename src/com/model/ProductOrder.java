package com.model;

public class ProductOrder {
    private int orderId;
    private UserSignIn userSignIn;
    private Product producto;

    public ProductOrder(UserSignIn userSignIn, Product producto) {
        this.userSignIn = userSignIn;
        this.producto = producto;
    }

}
