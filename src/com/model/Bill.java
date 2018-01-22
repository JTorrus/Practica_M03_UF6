package com.model;

import java.sql.Date;

public class Bill {
    private Order order;
    private Date date;
    private float finalPrice;

    public Bill(Order order, Date date, float finalPrice) {
        this.order = order;
        this.date = date;
        this.finalPrice = finalPrice;
    }
}
