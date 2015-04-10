package com.onesys.onemarket.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onesys.onemarket.R;
import com.onesys.onemarket.utils.BaseFragment;

public class SearchFragment extends BaseFragment {

	public SearchFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
         
        return rootView;
    }
}
