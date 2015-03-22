package com.onesys.onemarket.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class ProductStore
        implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String promotion_id;
    public String quantity;
    public String store_id;
    public String store_name;

    public ProductStore(JSONObject paramJSONObject)
    {

    }

    public String toString()
    {
        return "ProductStore [store_name=" + this.store_name + ", promotion_id=" + this.promotion_id + ", quantity=" + this.quantity + "]";
    }
}

