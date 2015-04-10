package com.onesys.onemarket.model;

import android.content.SharedPreferences;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

//        countTotalPrice();
    }




}
