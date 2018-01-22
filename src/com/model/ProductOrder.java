package com.model;

public class ProductOrder {
    private int orderId;
    private int userId;
    private int productId;

    public ProductOrder(int orderId, int userId, int productId) {
        this.orderId = orderId;
        this.userId = userId;
        this.productId = productId;
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
