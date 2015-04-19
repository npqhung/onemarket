package com.onesys.onemarket.utils.response;

import com.onesys.onemarket.model.Payment;
import com.onesys.onemarket.model.ProductDetailData;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class PaymentResponse {

    private boolean status;
    private int code;
    private String message;
    private Payment data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Payment getData() {
        return data;
    }

    public void setData(Payment data) {
        this.data = data;
    }
}
