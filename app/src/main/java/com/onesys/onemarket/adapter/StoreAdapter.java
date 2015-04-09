package com.onesys.onemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductStore;

import java.util.ArrayList;

/**
 * Created by Hung on 9/04/2015.
 */
public class StoreAdapter extends BaseAdapter
{
    private ArrayList<ProductStore> mStoreList = new ArrayList();
    private MainActivity context;

    public StoreAdapter(MainActivity context,ArrayList<ProductStore> storeList)
    {
        mStoreList = storeList;
        this.context = context;
    }

    public int getCount()
    {
        return this.mStoreList.size();
    }

    public ProductStore getItem(int paramInt)
    {
        return (ProductStore)this.mStoreList.get(paramInt);
    }

    public long getItemId(int paramInt)
    {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup){

        if (paramView == null)
            paramView = ((LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_dialog_store_item, null);
        ProductStore localProductStore = (ProductStore)this.mStoreList.get(paramInt);
        ((TextView)paramView.findViewById(R.id.tv_dialog_store_text)).setText(localProductStore.store_name);
        return paramView;
    }
}