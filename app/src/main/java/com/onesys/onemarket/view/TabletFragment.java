package com.onesys.onemarket.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.utils.Constants;

import java.util.ArrayList;

public class TabletFragment extends Fragment {
	
	public TabletFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_tablet_gridview, container, false);
        return rootView;
    }
}
