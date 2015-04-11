package com.onesys.onemarket.model;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.onesys.onemarket.utils.Constants;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hung on 10/04/2015.
 */
public class DataCart {

    private static final String TAG = "OneMarket";
    private ArrayList<CartItem> cartItems = new ArrayList();
    private int price;
    private int transportFee;

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(ArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public static DataCart readFromPreference(SharedPreferences sharedPreferences)
    {
        String dataCartStr = sharedPreferences.getString(Constants.DATA_CART,"");

        Log.i(TAG,"READ DataCart string : " + dataCartStr);

        if(dataCartStr.length() == 0){
            return null;
        }

        Gson gson = new Gson();
        DataCart dataCart = null;
        try{
            dataCart = gson.fromJson(dataCartStr, DataCart.class);
        }catch (JsonSyntaxException ex){
            ex.printStackTrace();
        }
        return  dataCart;
    }

    public static void saveToPreference(SharedPreferences sharedPreferences, DataCart dataCart) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String dataCartStr = gson.toJson(dataCart);

        Log.i(TAG,"SAVE DataCart string : " + dataCartStr);
        editor.putString(Constants.DATA_CART, dataCartStr);
        editor.commit();
    }

    public void addCart(ProductDetailData productDetail, String storeId){

        CartItem cartItem = new CartItem(productDetail, storeId);

        this.cartItems.add(cartItem);

        countTotalPrice();
    }

    public void countTotalPrice()
    {
        this.price = 0;
        this.transportFee = 0;

        for(CartItem item : cartItems){
            price += item.getCartItemPrice();
            transportFee += item.getTranportFee();
        }
    }

    public int getPrice()    {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTransportFee() {
        return transportFee;
    }

    public void setTransportFee(int transportFee) {
        this.transportFee = transportFee;
    }

    public int getTotalPrice()
    {
        return this.price + this.transportFee;
    }

}
