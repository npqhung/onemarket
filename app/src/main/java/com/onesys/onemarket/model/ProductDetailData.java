package com.onesys.onemarket.model;

/**
 * Created by Hung Nguyen on 3/29/2015.
 */


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;


public class ProductDetailData
        implements Serializable, Product{

    private static final long serialVersionUID = 1L;

    @JsonProperty("category_id")
    private String categoryId;

    private ArrayList<ColorObject> color = new ArrayList();

    private ArrayList<Object> images = new ArrayList();

    @JsonProperty("discount_price")
    private String discountPrice;

    private String id;
    // private ArrayList<ImageObject> imageObjectArray = new ArrayList();
    private String laptopinfo;

    @JsonProperty("product_store")
    private ArrayList<ProductStore> productStore = new ArrayList();

    @JsonProperty("manufactur_id")
    private String manufactureId;

    private SpecificationInfo mobileinfo;
    private String name;

    @JsonProperty("order_type")
    private String orderType;

    private String point;

    @JsonProperty("preorder_price")
    private String preorderPrice;

    private String price;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("rate_point")
    private String ratePoint;

    private String status;
    private String thumbnail;

    @JsonProperty("total_rate")
    private String totalRate;
    private String videos;

    @JsonProperty("views_count")
    private String viewsCount;

    @JsonProperty("warranty_manufactur_id")
    private String warrantyManufactureId;

    @JsonProperty("warranty_month")
    private String warrantyMonth;

//    private ArrayList<ColorObject> colorObjectArray = new ArrayList();

    public ArrayList<ColorObject> getColor() {
        return color;
    }

    public void setColor(ArrayList<ColorObject> color) {
        this.color = color;
    }



    public ArrayList<Object> getImages() {
        return images;
    }

    public void setImages(ArrayList<Object> images) {
        this.images = images;
    }



    public String getLaptopinfo() {
        return laptopinfo;
    }

    public void setLaptopinfo(String laptopinfo) {
        this.laptopinfo = laptopinfo;
    }

    public ArrayList<ProductStore> getProductStore() {
        return productStore;
    }

    public void setProductStore(ArrayList<ProductStore> productStore) {
        this.productStore = productStore;
    }

    public SpecificationInfo getMobileinfo() {
        return mobileinfo;
    }

    public void setMobileinfo(SpecificationInfo mobileinfo) {
        this.mobileinfo = mobileinfo;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(String manufactureId) {
        this.manufactureId = manufactureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPreorderPrice() {
        return preorderPrice;
    }

    public void setPreorderPrice(String preorderPrice) {
        this.preorderPrice = preorderPrice;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRatePoint() {
        return ratePoint;
    }

    public void setRatePoint(String ratePoint) {
        this.ratePoint = ratePoint;
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

    public String getTotalRate() {
        return totalRate;
    }

    public void setTotalRate(String totalRate) {
        this.totalRate = totalRate;
    }

    public String getVideos() {
        return videos;
    }

    public void setVideos(String videos) {
        this.videos = videos;
    }

    public String getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(String viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getWarrantyManufactureId() {
        return warrantyManufactureId;
    }

    public void setWarrantyManufactureId(String warrantyManufactureId) {
        this.warrantyManufactureId = warrantyManufactureId;
    }

    public String getWarrantyMonth() {
        return warrantyMonth;
    }

    public void setWarrantyMonth(String warrantyMonth) {
        this.warrantyMonth = warrantyMonth;
    }

    public float getRatePointFloat(){
        return Float.parseFloat(this.ratePoint);
    }
}

