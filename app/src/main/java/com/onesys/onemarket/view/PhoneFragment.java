package com.onesys.onemarket.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.utils.Constants;

import java.util.ArrayList;

public class PhoneFragment extends Fragment implements View.OnClickListener{
    private static final String TAG = "OneMarket";

    private boolean isGridShow = false;
    private ImageView ivGridListType;
    private GridView phoneGridView;

    public PhoneFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_phone_gridview, container, false);

        initGridView(rootView);

        return rootView;
    }

    public void onAttach(Activity paramActivity)
    {
        super.onAttach(paramActivity);
    }

    private void initGridView(View view) {
        phoneGridView = (GridView) view.findViewById(R.id.gv_phone);

        phoneGridView.setAdapter(new ProductAdapter(view.getContext()));

        phoneGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //show product_detail view
                ProductData product = (ProductData)phoneGridView.getAdapter().getItem(position);
                showProductDetailView(product);
            }
        });

        this.ivGridListType = ((ImageView)view.findViewById(R.id.iv_phone_gridlist));
        this.ivGridListType.setOnClickListener(this);
    }

    private void showProductDetailView(ProductData product){

        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PRODUCT_STRING,product);

        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame_container, productDetailFragment).commit();


    }

    private ArrayList<ProductData> buildProductList(){
        ArrayList<ProductData> productList = new ArrayList<ProductData>();

        ProductData product1 = new ProductData();
        product1.setImageId(R.drawable.temp_phone1);
        product1.setPrice("1.999.000");
        productList.add(product1);

        ProductData product2 = new ProductData();
        product2.setImageId(R.drawable.temp_phone2);
        product2.setPrice("2.999.000");
        productList.add(product2);

        ProductData product3 = new ProductData();
        product3.setImageId(R.drawable.temp_phone3);
        product3.setPrice("3.999.000");
        productList.add(product3);

        ProductData product4 = new ProductData();
        product4.setImageId(R.drawable.temp_phone4);
        product4.setPrice("4.999.000");
        productList.add(product4);

        ProductData product5 = new ProductData();
        product5.setImageId(R.drawable.temp_phone5);
        product5.setPrice("5.999.000");
        productList.add(product5);

        ProductData product6 = new ProductData();
        product6.setImageId(R.drawable.temp_phone6);
        product6.setPrice("6.999.000");
        productList.add(product6);

        return productList;

    }

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            case R.id.iv_phone_gridlist :
                Log.i(TAG, "Entered onItemSelected");
                updatePhoneView(paramView);
                break;
            default:
                return;

        }

    }

    private void updatePhoneView(View paramView){

            if (this.isGridShow){
                ivGridListType.setImageResource(R.drawable.ic_gridlist_menu_list);
                phoneGridView.setNumColumns(2);
                this.isGridShow = false;
            }
            else{
                phoneGridView.setNumColumns(1);
                this.isGridShow = false;
            }
    }
}
