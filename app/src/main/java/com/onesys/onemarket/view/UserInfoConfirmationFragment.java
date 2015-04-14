package com.onesys.onemarket.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.SpinnerItem;
import com.onesys.onemarket.utils.BaseFragment;

import java.util.ArrayList;

public class UserInfoConfirmationFragment extends BaseFragment
        implements View.OnClickListener{

    private static final String TAG = "OneMarket";

	public UserInfoConfirmationFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.layout_user_info_confirmation, container, false);

        ImageView mBackIcon = ((ImageView)rootView.findViewById(R.id.userinfo_top_menu_left));
        mBackIcon.setOnClickListener(this);

        //paymentType
        Spinner paymentTypeSpinner  = (Spinner)rootView.findViewById(R.id.userinfo_payment_type);
        ArrayList<SpinnerItem> paymentTypeList = new ArrayList<SpinnerItem>();
        paymentTypeList.add(new SpinnerItem(1, getString(R.string.str_user_info_paymenttype_cash)));
        paymentTypeList.add(new SpinnerItem(3,getString(R.string.str_user_info_paymenttype_online)));

        ArrayAdapter<SpinnerItem> paymentTypeAdapter = new ArrayAdapter<SpinnerItem>
                (getActivity(), android.R.layout.simple_spinner_item,paymentTypeList);

        paymentTypeAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        paymentTypeSpinner.setAdapter(paymentTypeAdapter);
        paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,long id){

            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView){
            }
        });

        //city
        Spinner citySpinner = (Spinner)rootView.findViewById(R.id.userinfo_city);
        ArrayList<SpinnerItem> cityList = new ArrayList<SpinnerItem>();
        cityList.add(new SpinnerItem(1,"Tp Ho Chi Minh"));
        cityList.add(new SpinnerItem(2,"Binh Duong"));

        ArrayAdapter<SpinnerItem> cityAdapter = new ArrayAdapter<SpinnerItem>
                (getActivity(), android.R.layout.simple_spinner_item,cityList);

        cityAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        citySpinner.setAdapter(cityAdapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {

            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {
            }
        });

        //deliveryType
        Spinner deliveryTypeSpinner = ((Spinner)rootView.findViewById(R.id.userinfo_delivery_type));
        ArrayList<SpinnerItem> deliveryList = new ArrayList<SpinnerItem>();
        deliveryList.add(new SpinnerItem(1,"At Home"));
        deliveryList.add(new SpinnerItem(2,"In Store"));

        ArrayAdapter<SpinnerItem> deliveryAdapter = new ArrayAdapter<SpinnerItem>
                (getActivity(), android.R.layout.simple_spinner_item,deliveryList);

        deliveryAdapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        deliveryTypeSpinner.setAdapter(deliveryAdapter);

        deliveryTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int pos,long id){

            }

            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView){
            }
        });
         
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

    private void callBack(){
        ((MainActivity)getActivity()).showContent(MainActivity.CART_VIEW);
    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {

            case R.id.userinfo_top_menu_left:
                callBack();
                break;

            case R.id.userinfo_confirm :
                confirmPayment(paramView);
                break;
            default:
                return;
        }
    }

    private void confirmPayment(View view){

        TextView userInfoAddress = (TextView)view.findViewById(R.id.userinfo_address);

        if (TextUtils.isEmpty(userInfoAddress.getText().toString())){
            Toast.makeText(getActivity(), getString(R.string.error_userinfo_address), Toast.LENGTH_SHORT).show();
            return;
        }

        TextView userInfoPhone = (TextView)view.findViewById(R.id.userinfo_phone);
        if (TextUtils.isEmpty(userInfoPhone.getText().toString())){
            Toast.makeText(getActivity(), getString(R.string.error_userinfo_phone), Toast.LENGTH_SHORT).show();
            return;
        }

        TextView userInfoName = (TextView)view.findViewById(R.id.userinfo_name);
        if (TextUtils.isEmpty(userInfoName.getText().toString())){
            Toast.makeText(getActivity(), getString(R.string.error_userinfo_name), Toast.LENGTH_SHORT).show();
            return;
        }

        TextView userInfoEmail = (TextView)view.findViewById(R.id.userinfo_email);
        if (TextUtils.isEmpty(userInfoEmail.getText().toString()))
        {
            Toast.makeText(getActivity(), getString(R.string.error_userinfo_email), Toast.LENGTH_SHORT).show();
            return;
        }

        SpinnerItem citySpinner = (SpinnerItem)((Spinner)view.findViewById(R.id.userinfo_city)).getSelectedItem();
        if (citySpinner == null){
            Toast.makeText(getActivity(), getString(R.string.error_userinfo_city), Toast.LENGTH_SHORT).show();
            return;
        }

        SpinnerItem paymentTypeSpinner = (SpinnerItem)((Spinner)view.findViewById(R.id.userinfo_payment_type)).getSelectedItem();
        if (paymentTypeSpinner == null){
            Toast.makeText(getActivity(), getString(R.string.error_userinfo_paymenttype), Toast.LENGTH_SHORT).show();
            return;
        }

        SpinnerItem deliveryTypeSpinner = (SpinnerItem)((Spinner)view.findViewById(R.id.userinfo_delivery_type)).getSelectedItem();
        if (deliveryTypeSpinner == null){
            Toast.makeText(getActivity(), getString(R.string.error_userinfo_deliverytype), Toast.LENGTH_SHORT).show();
            return;
        }


//        final int i = this.mPhuongThucThanhToan.id;
//        String str1 = this.dia_chi_giao_hang.getText().toString();
//        String str2 = this.mCity.getId();
//        String str3 = this.dien_thoai.getText().toString();
//        String str4 = this.ghi_chu_khac.getText().toString();
//        String str5 = this.email.getText().toString();
//        String str6 = this.ho_va_ten.getText().toString();
//        String str7 = this.mPhuongThucNhanHangData.getId();
//
//        new PreparePaymentTask(getActivity(), i, str1, str2, str3, str4, str5, str6, str7)
//        {
//            protected void onPostExecute(String paramAnonymousString)
//            {
//                super.onPostExecute(paramAnonymousString);
//                ((MobileMarketActivity)GioHangThanhToanFragment.this.getActivity()).updateGioHangTab();
//                if (i == 3)
//                {
//                    Intent localIntent = new Intent(GioHangThanhToanFragment.this.getActivity(), WebviewThanhtoan.class);
//                    localIntent.putExtra(WebviewThanhtoan.URL_STRING, paramAnonymousString);
//                    GioHangThanhToanFragment.this.startActivityForResult(localIntent, 0);
//                    return;
//                }
//                new LoadAPIThanhToan(GioHangThanhToanFragment.this.getActivity())
//                {
//                    protected void onPostExecute(Payment paramAnonymous2Payment)
//                    {
//                        super.onPostExecute(paramAnonymous2Payment);
//                        if (paramAnonymous2Payment != null)
//                        {
//                            ((MobileMarketApplication)GioHangThanhToanFragment.this.getActivity().getApplication()).clearDataCart();
//                            ((MobileMarketActivity)GioHangThanhToanFragment.this.getActivity()).updateGioHangTab();
//                            Toast.makeText(GioHangThanhToanFragment.this.getActivity(), "Giao dich thanh cong", 0).show();
//                            GioHangThanhToanFragment.this.callBack();
//                            return;
//                        }
//                        Toast.makeText(GioHangThanhToanFragment.this.getActivity(), "Giao dich bi huy bo", 0).show();
//                        GioHangThanhToanFragment.this.callBack();
//                    }
//                }
//                        .execute(new String[] { paramAnonymousString });
//            }
//        }
//                .execute(new String[0]);

    }
}
