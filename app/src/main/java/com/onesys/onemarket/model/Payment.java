package com.onesys.onemarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Hung Nguyen on 4/19/2015.
 */

public class Payment
{
    @JsonProperty("order_id")
    private String orderId;

    private String paymenttype;
    private String point;

    @JsonProperty("total_Price")
    private String totalPrice;

    @JsonProperty("user_id")
    private String userId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

