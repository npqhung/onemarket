package com.onesys.onemarket.model;

import java.util.ArrayList;

/**
 * Created by Hung on 21/04/2015.
 */
public interface Product {
    public String getOrderType();
    public String getPreorderPrice();
    public String getPrice();
    public ArrayList<ColorObject> getColor();
    public String getName();
}
