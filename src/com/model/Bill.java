package com.model;

import java.sql.Date;

public class Bill {
    private int billId;
    private int orderId;
    private Date billDate;
    private Double finalPrice;

    public Bill(int billId, int orderId, Date billDate, Double finalPrice) {
        this.billId = billId;
        this.orderId = orderId;
        this.billDate = billDate;
        this.finalPrice = finalPrice;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }
}
