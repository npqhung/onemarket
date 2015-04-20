package com.onesys.onemarket.utils.response;


import com.onesys.onemarket.model.TabletDetail;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class TabletDetailResponse {

    private boolean status;
    private int code;
    private String message;
    private TabletDetail data;

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

    public TabletDetail getData() {
        return data;
    }

    public void setData(TabletDetail data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
