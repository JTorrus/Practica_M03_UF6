package com.model;

import java.sql.Date;

public class ProductOrder {
    private int orderId;
    private int userId;
    private int productId;
    private Date orderDate;
    private Double finalPrice;

    public ProductOrder(int orderId, int userId, int productId, Date orderDate, Double finalPrice) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
        this.orderDate = orderDate;
        this.finalPrice = finalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Double getFinalPrice() {
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
