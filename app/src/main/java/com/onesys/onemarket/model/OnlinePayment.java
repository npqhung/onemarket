package com.onesys.onemarket.model;

/**
 * Created by Hung Nguyen on 4/19/2015.
 */
public class OnlinePayment {

    private String amount;
    private String locale;
    private String merchTxnRef;
    private String message;
    private String orderInfo;
    private String status;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getMerchTxnRef() {
        return merchTxnRef;
    }

    public void setMerchTxnRef(String merchTxnRef) {
        this.merchTxnRef = merchTxnRef;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(String orderInfo) {
        this.orderInfo = orderInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
