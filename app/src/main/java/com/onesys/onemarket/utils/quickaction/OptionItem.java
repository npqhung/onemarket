package com.onesys.onemarket.utils.quickaction;

public class OptionItem
{

    public static final int OPTION_BRAND = 1;
    public static final int OPTION_KHUYENMAI = 5;
    public static final int OPTION_OS = 2;
    public static final int OPTION_PRICE = 0;
    public static final int OPTION_SCREEN = 3;
    public static final int OPTION_TINHNANG = 4;
    public String id;
    public String name;

    public OptionItem()
    {
        id = "-1";
        name = "Cancel";
    }

    public OptionItem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString()
    {
        return (new StringBuilder("OptionItem [id=")).append(id).append(", name=").append(name).append("]").toString();
    }
}
