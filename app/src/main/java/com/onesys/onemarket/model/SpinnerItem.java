package com.onesys.onemarket.model;

/**
 * Created by Hung on 14/04/2015.
 */
public class SpinnerItem
{
    public int id;
    public String name;

    public SpinnerItem(int id, String name)    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;            // What to display in the Spinner list.
    }
}