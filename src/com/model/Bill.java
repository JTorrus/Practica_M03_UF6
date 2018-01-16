package com.model;

import java.sql.Date;

public class Bill {
    private int billId;
    private ProductOrder productOrder;
    private Date date;
    private float finalPrice;

    public Bill(int billId, ProductOrder productOrder, Date date, float finalPrice) {
        this.billId = billId;
        this.productOrder = productOrder;
        this.date = date;
        this.finalPrice = finalPrice;
    }
}
