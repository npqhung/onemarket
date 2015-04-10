package com.onesys.onemarket.model;

/**
 * Created by Hung on 10/04/2015.
 */
public class CartItem {

    private ProductDetailData productDetail;
    private String storeId;
    private ColorObject colorObject;

    private int tranportFee = 20000;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity = 0;

    public CartItem(ProductDetailData productDetail, String storeId)
    {
        this.storeId = storeId;
        this.productDetail = productDetail;
        this.colorObject = productDetail.getColor().get(0);
    }

    public ProductDetailData getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailData productDetail) {
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

        if ((productDetail == null) || (quantity == 0)) {
            return 0;
        }

        if (Integer.parseInt(productDetail.getOrder_type()) == 2){//preOrder
            try
            {
                totalPrice = Integer.parseInt(productDetail.getPreorder_price()) * quantity;
            }
            catch (Exception exception) {

            }
        }else{
            try {
                totalPrice = Integer.parseInt(productDetail.getPrice()) * quantity;
            }catch (Exception e){

            }
        }

        return totalPrice;
    }
}
