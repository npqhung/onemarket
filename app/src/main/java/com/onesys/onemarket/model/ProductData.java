package com.onesys.onemarket.model;

import android.widget.ImageView;

import java.io.Serializable;

/**
 * Created by Hung on 12/03/2015.
 */
public class ProductData implements Serializable {
    private int imageId;
    private String price;

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
