package com.onesys.onemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.onesys.onemarket.utils.quickaction.OptionItem;

import java.util.ArrayList;

/**
 * Created by Hung on 7/04/2015.
 */
public class DialogAdapter extends BaseAdapter {
    Context context;
    private LayoutInflater mInflater;
    ArrayList<OptionItem> objects;
    int textViewResourceId;

    public DialogAdapter(Context context1, int i, ArrayList arraylist) {
        context = context1;
        textViewResourceId = i;
        objects = arraylist;
        mInflater = (LayoutInflater) context1.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return this.objects.size();
    }

    public OptionItem getItem(int paramInt) {
        return (OptionItem) this.objects.get(paramInt);
    }

    public long getItemId(int paramInt) {
        return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        if (paramView == null)
            paramView = this.mInflater.inflate(this.textViewResourceId, null);
        ((TextView) paramView).setText(getItem(paramInt).name);
        return paramView;
    }
}
