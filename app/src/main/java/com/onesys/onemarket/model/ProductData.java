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
    private String category_id;
    private String discount_price;
    private String id;
    private ArrayList<Promotion> promotion = new ArrayList();
    private ArrayList<ProductStore> product_store = new ArrayList();
    private String manufactur_id;
    private String name;
    private String os_id;
    private String price;
    private String product_type;
    private String rate_point;
    private String status;
    private String thumbnail;
    private String total_rate;
    private String views_count;
    private String warranty_manufactur_id;
    private String warranty_month;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String categoryId) {
        this.category_id = categoryId;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discountPrice) {
        this.discount_price = discountPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Promotion> getPromotion() {
        return promotion;
    }

    public void setPromotion(ArrayList<Promotion> promotion) {
        this.promotion = promotion;
    }

    public ArrayList<ProductStore> getProduct_store() {
        return product_store;
    }

    public void setProduct_store(ArrayList<ProductStore> listStore) {
        this.product_store = listStore;
    }

    public String getManufactur_id() {
        return manufactur_id;
    }

    public void setManufactur_id(String manufacturId) {
        this.manufactur_id = manufacturId;
    }

    public String getOs_id() {
        return os_id;
    }

    public void setOs_id(String os_id) {
        this.os_id = os_id;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String productType) {
        this.product_type = productType;
    }

    public String getRate_point() {
        return rate_point;
    }

    public void setRate_point(String ratePoint) {
        this.rate_point = ratePoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTotal_rate() {
        return total_rate;
    }

    public void setTotal_rate(String totalRate) {
        this.total_rate = totalRate;
    }

    public String getViews_count() {
        return views_count;
    }

    public void setViews_count(String views_count) {
        this.views_count = views_count;
    }

    public String getWarranty_manufactur_id() {
        return warranty_manufactur_id;
    }

    public void setWarranty_manufactur_id(String warrantyManufacturId) {
        this.warranty_manufactur_id = warrantyManufacturId;
    }

    public String getWarranty_month() {
        return warranty_month;
    }

    public void setWarranty_month(String warrantyMonth) {
        this.warranty_month = warrantyMonth;
    }

    public float getRatePointFloat()
    {
        if(this.rate_point == null)
            return 0;
        return Float.parseFloat(this.rate_point);
    }
}
