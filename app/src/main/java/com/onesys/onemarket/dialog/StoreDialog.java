package com.onesys.onemarket.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.StoreAdapter;
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.model.ProductStore;
import com.onesys.onemarket.utils.Constants;

/**
 * Created by Hung on 9/04/2015.
 */

public class StoreDialog extends DialogFragment
{
    public StoreListener storeListener;
    public StoreAdapter mAdapter;
    public ListView mListView;
    ProductDetailData productDetailData;

    public StoreDialog(){

    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        View localView = paramLayoutInflater.inflate(R.layout.layout_dialog_store_choice, paramViewGroup, false);

        Bundle args = getArguments();
        productDetailData = (ProductDetailData) args
                .getSerializable(Constants.PRODUCT_STRING);
        storeListener = (StoreListener)args.getSerializable(Constants.STORE_LISTENER);

        getDialog().setTitle(R.string.str_choose_store);
        this.mListView = ((ListView)localView.findViewById(R.id.dialog_choose_store_listview));
        this.mAdapter = new StoreAdapter((MainActivity)getActivity(), productDetailData.getProductStore());
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
                storeListener.onChooseStore((ProductStore) productDetailData.getProductStore().get(paramAnonymousInt));
                dismiss();
            }
        });
        return localView;
    }

    public abstract interface StoreListener
    {
        public abstract void onChooseStore(ProductStore paramProductStore);
    }
}
