package com.model;

import java.sql.Date;

public class Bill {
    private int billId;
    private int orderId;
    private Date date;
    private float finalPrice;

    public Bill(int billId, int orderId, Date date, float finalPrice) {
        this.billId = billId;
        this.orderId = orderId;
        this.date = date;
        this.finalPrice = finalPrice;
    }
}
