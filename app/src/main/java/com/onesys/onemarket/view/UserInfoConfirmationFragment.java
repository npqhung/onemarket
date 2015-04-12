package com.onesys.onemarket.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onesys.onemarket.R;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.utils.BaseFragment;

public class UserInfoConfirmationFragment extends BaseFragment {

    private static final String TAG = "OneMarket";

	public UserInfoConfirmationFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.layout_user_info_confirmation, container, false);
         
        return rootView;
    }

    public void onFragmentUnselected()    {
        Log.i(TAG,"UserInfoConfirmationFragment - onFragmentUnselected");
        super.onFragmentUnselected();
    }

    public void reloadData(){
//        loadCity();
        loadData();
    }

    private void loadData(){
        OneMarketApplication application = (OneMarketApplication)getActivity().getApplication();
//        this.ho_va_ten.setText(application.getAccountInfo().getFullname());
//        this.dien_thoai.setText(application.getAccountInfo().getPhoneNumber());
//        this.email.setText(application.getAccountInfo().getEmail());
//        this.nhanHangAdapter.setData(application.listPhuongThucNhanHang);
    }
}
