package com.onesys.onemarket.model;

/**
 * Created by Hung on 10/04/2015.
 */
public class CartItem {

    private ProductDetailData productDetail;
    private String storeId;

    public CartItem(ProductDetailData productDetail, String storeId)
    {
        this.storeId = storeId;
        this.productDetail = productDetail;
    }
}
