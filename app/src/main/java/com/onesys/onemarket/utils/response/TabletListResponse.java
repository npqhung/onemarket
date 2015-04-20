package com.onesys.onemarket.utils.response;
import com.onesys.onemarket.model.Tablet;

public class TabletListResponse {

    private boolean status;
    private int code;
    private String message;
    private Tablet[] data;

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

    public Tablet[] getData() {
        return data;
    }

    public void setData(Tablet[] data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
