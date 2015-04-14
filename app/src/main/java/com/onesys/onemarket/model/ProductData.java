package com.onesys.onemarket.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.text.DecimalFormat;
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

    @JsonProperty("category_id")
    private String categoryId;

    @JsonProperty("discount_price")
    private String discountPrice;
    private String id;

    private ArrayList<Promotion> promotion = new ArrayList();

    @JsonProperty("product_store")
    private ArrayList<ProductStore> productStore = new ArrayList();

    @JsonProperty("manufactur_id")
    private String manufactureId;
    private String name;

    @JsonProperty("os_id")
    private String osId;
    private String price;

    @JsonProperty("product_type")
    private String productType;

    @JsonProperty("rate_point")
    private String ratePoint;

    private String status;
    private String thumbnail;

    @JsonProperty("total_rate")
    private String totalRate;

    @JsonProperty("views_count")
    private String viewsCount;

    @JsonProperty("warranty_manufactur_id")
    private String warrantyManufactureId;

    @JsonProperty("warranty_month")
    private String warrantyMonth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public ArrayList<Promotion> getPromotion() {
        return promotion;
    }

    public void setPromotion(ArrayList<Promotion> promotion) {
        this.promotion = promotion;
    }

    public ArrayList<ProductStore> getProductStore() {
        return productStore;
    }

    public void setProductStore(ArrayList<ProductStore> listStore) {
        this.productStore = listStore;
    }

    public String getManufactureId() {
        return manufactureId;
    }

    public void setManufactureId(String manufacturId) {
        this.manufactureId = manufacturId;
    }

    public String getOsId() {
        return osId;
    }

    public void setOsId(String osId) {
        this.osId = osId;
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

    public String getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(String viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getWarrantyManufactureId() {
        return warrantyManufactureId;
    }

    public void setWarrantyManufactureId(String warrantyManufacturId) {
        this.warrantyManufactureId = warrantyManufacturId;
    }

    public String getWarrantyMonth() {
        return warrantyMonth;
    }

    public void setWarrantyMonth(String warrantyMonth) {
        this.warrantyMonth = warrantyMonth;
    }

    public float getRatePointFloat()
    {
        if(this.ratePoint == null)
            return 0;
        return Float.parseFloat(this.ratePoint);
    }

    public String getPriceFormat()
    {
        Double localDouble = Double.valueOf(Double.parseDouble(this.price));
        return new DecimalFormat("#,###,### " + "VND").format(localDouble);
    }

    public String afterDiscount(){
        int price = Integer.parseInt(this.price);
        int discountPrice = Integer.parseInt(this.discountPrice);

        if(price == 0){
            return "0.0";
        }

        double percentDiscount = (price - discountPrice) * 100/ price;

        return "" + percentDiscount;
    }
}
