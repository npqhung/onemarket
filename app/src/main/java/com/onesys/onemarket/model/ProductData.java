package com.onesys.onemarket.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class ProductData implements Serializable {
    private int imageId;

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
    private String categoryId;
    private String discountPrice;
    private String id;
    private ArrayList<Promotion> listPromotions = new ArrayList();
    private ArrayList<ProductStore> listStore = new ArrayList();
    private String manufacturId;
    private String name;
    private String os_id;
    private String price;
    private String productType;
    private String ratePoint;
    private String status;
    private String thumbnail;
    private String totalRate;
    private String views_count;
    private String warrantyManufacturId;
    private String warrantyMonth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
