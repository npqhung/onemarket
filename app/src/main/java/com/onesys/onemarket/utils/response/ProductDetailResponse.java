package com.onesys.onemarket.utils.response;

import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.model.ProductDetailData;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class ProductDetailResponse {

    private boolean status;
    private int code;
    private String message;
    private ProductDetailData data;

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

    public ProductDetailData getData() {
        return data;
    }

    public void setData(ProductDetailData data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
