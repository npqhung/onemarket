package com.onesys.onemarket.model;

/**
 * Created by Hung on 10/04/2015.
 */
public class CartItem {

    private Product productDetail;
    private String storeId;
    private ColorObject colorObject;

    public int getTranportFee() {
        return tranportFee;
    }

    public void setTranportFee(int tranportFee) {
        this.tranportFee = tranportFee;
    }

    private int tranportFee = 20000;

    public Integer getQuantity() {
        return 1;
    }

    public CartItem(Product productDetail, String storeId)
    {
        this.storeId = storeId;
        this.productDetail = productDetail;
        this.colorObject = productDetail.getColor().get(0);
    }

    public Product getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(Product productDetail) {
        this.productDetail = productDetail;
    }

    public ColorObject getColorObject() {
        return colorObject;
    }

    public void setColorObject(ColorObject colorObject) {
        this.colorObject = colorObject;
    }

    public int getCartItemPrice(){
        int totalPrice = 0;

        if ((this.productDetail == null)) {
            return 0;
        }

        if (Integer.parseInt(productDetail.getOrderType()) == 2){//preOrder
            try
            {
                totalPrice = Integer.parseInt(productDetail.getPreorderPrice()) * 1;
            }
            catch (Exception exception) {

            }
        }else{
            try {
                totalPrice = Integer.parseInt(productDetail.getPrice()) * 1;
            }catch (Exception e){

            }
        }

        return totalPrice;
    }
}
