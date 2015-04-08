package com.onesys.onemarket.utils;

import com.onesys.onemarket.utils.quickaction.OptionItem;

import java.util.ArrayList;

/**
 * Created by Hung on 8/04/2015.
 */
public class SearchByUtils {

    private static ArrayList<OptionItem> getMobilePriceList(){
        ArrayList<OptionItem> priceList = new ArrayList<OptionItem>();
        priceList.add(new OptionItem("1","Under 1 Mil "));
        priceList.add(new OptionItem("2","From 1 Mil - 2 Mil"));
        priceList.add(new OptionItem("3","From 2 Mil - 3 Mil"));
        priceList.add(new OptionItem("4","From 3 Mil - 4 Mil"));
        priceList.add(new OptionItem("5","From 4 Mil - 6 Mil"));
        priceList.add(new OptionItem("6","From 6 Mil - 8 Mil"));
        priceList.add(new OptionItem("7","From 8 Mil - 10 Mil"));
        priceList.add(new OptionItem("8","Over 10 Mil"));
        priceList.add(new OptionItem("-1","Cancel"));

        return priceList;
    }

    private  static ArrayList<OptionItem> getMobileBrandList(){
        ArrayList<OptionItem> brandList = new ArrayList<OptionItem>();

        brandList.add(new OptionItem("1", "Apple"));
        brandList.add(new OptionItem("2", "Nokia"));
        brandList.add(new OptionItem("3", "Samsung"));
        brandList.add(new OptionItem("4", "HTC"));
        brandList.add(new OptionItem("5", "BlackBerry"));
        brandList.add(new OptionItem("27", "LG"));
        brandList.add(new OptionItem("28", "OPPO"));
        brandList.add(new OptionItem("30", "GIONEE"));
        brandList.add(new OptionItem("31", "ASUS"));
        brandList.add(new OptionItem("32", "HAIER"));
        brandList.add(new OptionItem("33", "POLAROID"));
        brandList.add(new OptionItem("34", "SONY"));
        brandList.add(new OptionItem("35", "LENOVO"));
        brandList.add(new OptionItem("36", "ACER"));
        brandList.add(new OptionItem("37", "DELL"));
        brandList.add(new OptionItem("38", "ALCATEL"));
        brandList.add(new OptionItem("41", "WIKO"));
        brandList.add(new OptionItem("-1", "Cancel"));

        return brandList;

    }


    public static ArrayList<OptionItem> getMobileOptionList(int position){

        ArrayList<OptionItem> optionList = new ArrayList<OptionItem>();
        switch (position){
            case 0: //price
                optionList = getMobilePriceList();
                break;
            case 1: //brand
                optionList = getMobileBrandList();
                break;
        }
        return optionList;
    }
}
