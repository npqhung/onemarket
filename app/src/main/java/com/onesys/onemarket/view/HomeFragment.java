package com.onesys.onemarket.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onesys.onemarket.R;
import com.onesys.onemarket.utils.BaseFragment;

public class HomeFragment extends BaseFragment {

    private static final String TAG = "OneMarket";

	public HomeFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
         
        return rootView;
    }

    public void onFragmentUnselected()    {
        Log.i(TAG,"HomeFragment - onFragmentUnselected");
        super.onFragmentUnselected();
    }
}
