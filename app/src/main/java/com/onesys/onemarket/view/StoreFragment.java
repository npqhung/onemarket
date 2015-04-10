package com.onesys.onemarket.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onesys.onemarket.R;
import com.onesys.onemarket.utils.BaseFragment;

public class StoreFragment extends BaseFragment {
	
	public StoreFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_store, container, false);
         
        return rootView;
    }
}
