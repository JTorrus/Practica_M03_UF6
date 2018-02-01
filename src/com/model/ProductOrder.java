package com.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductOrder {
    private int orderId;
    private int userId;
    private int productId;
    private Timestamp orderDate;
    private float finalPrice;

    public ProductOrder(int userId, int productId, float finalPrice) {
        this.userId = userId;
        this.productId = productId;
        this.finalPrice = finalPrice;
        this.orderDate = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public float getFinalPrice() {
        return finalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
