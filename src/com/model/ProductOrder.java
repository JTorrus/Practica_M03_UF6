package com.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ProductOrder {
    private int orderId;
    private int userId;
    private int productId;
    private Timestamp orderDate;
    private float finalPrice;

    public ProductOrder(int orderId, int userId, int productId, Timestamp orderDate, float finalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.finalPrice = finalPrice;
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
