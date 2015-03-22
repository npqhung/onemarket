package com.onesys.onemarket.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.onesys.onemarket.R;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.task.LoadProductTask;

public class LaptopFragment extends Fragment {

    private OneMarketApplication application = null;
    public LaptopFragment(){}

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        application = ((OneMarketApplication) getActivity().getApplication());
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_laptop, container, false);

        loadProductList();
        return rootView;
    }

    public void loadProductList(){
        application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadProductTask().execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }
}
