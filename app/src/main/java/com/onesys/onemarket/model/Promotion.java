package com.onesys.onemarket.model;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class Promotion
        implements Serializable
{
    private static final long serialVersionUID = 1L;
    public String created_date;
    public String description;
    public String modified_date;
    public String name;
    public String promotion_id;
    public String quantity;

    public Promotion()
    {

    }

    public String toString()
    {
        return "Promotion [promotion_id=" + this.promotion_id + ", name=" + this.name + ", quantity=" + this.quantity + ", description=" + this.description + ", created_date=" + this.created_date + ", modified_date=" + this.modified_date + "]";
    }
}