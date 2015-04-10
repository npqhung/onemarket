package com.onesys.onemarket.model;

/**
 * Created by Hung Nguyen on 3/29/2015.
 */


import android.content.Context;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


public class ProductDetailData
        implements Serializable{

    private static final long serialVersionUID = 1L;
    private String category_id;
//    private ArrayList<ColorObject> colorObjectArray = new ArrayList();

    public ArrayList<ColorObject> getColor() {
        return color;
    }

    public void setColor(ArrayList<ColorObject> color) {
        this.color = color;
    }

    private ArrayList<ColorObject> color = new ArrayList();

    public ArrayList<Object> getImages() {
        return images;
    }

    public void setImages(ArrayList<Object> images) {
        this.images = images;
    }

    private ArrayList<Object> images = new ArrayList();

    private String discount_price;
    private String id;
   // private ArrayList<ImageObject> imageObjectArray = new ArrayList();
    private String laptopinfo;

    public String getLaptopinfo() {
        return laptopinfo;
    }

    public void setLaptopinfo(String laptopinfo) {
        this.laptopinfo = laptopinfo;
    }

    public ArrayList<ProductStore> getProduct_store() {
        return product_store;
    }

    public void setProduct_store(ArrayList<ProductStore> product_store) {
        this.product_store = product_store;
    }

    private ArrayList<ProductStore> product_store = new ArrayList();
    private String manufactur_id;


    public SpecificationInfo getMobileinfo() {
        return mobileinfo;
    }

    public void setMobileinfo(SpecificationInfo mobileinfo) {
        this.mobileinfo = mobileinfo;
    }

    private SpecificationInfo mobileinfo;
    private String name;
    private String order_type;
    private String point;
    private String preorder_price;
    private String price;
    private String product_type;
    private String rate_point;
    private String status;
    private String thumbnail;
    private String total_rate;
    private String videos;
    private String views_count;
    private String warranty_manufactur_id;
    private String warranty_month;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(String discount_price) {
        this.discount_price = discount_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufactur_id() {
        return manufactur_id;
    }

    public void setManufactur_id(String manufactur_id) {
        this.manufactur_id = manufactur_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPreorder_price() {
        return preorder_price;
    }

    public void setPreorder_price(String preorder_price) {
        this.preorder_price = preorder_price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getRate_point() {
        return rate_point;
    }

    public void setRate_point(String rate_point) {
        this.rate_point = rate_point;
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

    public void setTotal_rate(String total_rate) {
        this.total_rate = total_rate;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
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

    public void setWarranty_manufactur_id(String warranty_manufactur_id) {
        this.warranty_manufactur_id = warranty_manufactur_id;
    }

    public String getWarranty_month() {
        return warranty_month;
    }

    public void setWarranty_month(String warranty_month) {
        this.warranty_month = warranty_month;
    }

    public float getRatePointFloat(){
        return Float.parseFloat(this.rate_point);
    }
}

