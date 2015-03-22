package com.onesys.onemarket.utils;

import com.onesys.onemarket.model.ProductData;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class ProductListResponse {

    private boolean status;
    private int code;
    private String message;
    private ProductData[] data;

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

    public ProductData[] getData() {
        return data;
    }

    public void setData(ProductData[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
