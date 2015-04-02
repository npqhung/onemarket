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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.task.LoadPhoneDetailTask;
//import com.onesys.onemarket.task.LoadPhoneProductTask;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.quickaction.ActionItem;
import com.onesys.onemarket.utils.quickaction.SearchByQuickAction;

public class PhoneFragment extends Fragment implements View.OnClickListener{

    private OneMarketApplication application = null;
    private ProductAdapter productAdapter;

    private static final String TAG = "OneMarket";

    private boolean isGridShow = false;
    private ImageView ivPhoneGridListType;
    private GridView phoneGridView;
    private MainActivity context;

    private SearchByQuickAction searchByQuickAction;

    public PhoneFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_phone_gridview, container, false);
        context = (MainActivity)getActivity();

        productAdapter = new ProductAdapter(context);

        initPhoneGridView(rootView);

        initQuickAction();

        return rootView;
    }

    public void onAttach(Activity paramActivity)
    {
        super.onAttach(paramActivity);
    }

    private void initPhoneGridView(View view) {
        phoneGridView = (GridView) view.findViewById(R.id.gv_phone);
        phoneGridView.setNumColumns(2);

        loadProductList();

        phoneGridView.setAdapter(productAdapter);

        phoneGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //show product_detail view
                ProductData product = (ProductData) phoneGridView.getAdapter().getItem(position);
                showProductDetailView(product);
            }
        });

        this.ivPhoneGridListType = ((ImageView)view.findViewById(R.id.iv_phone_gridlist));
        this.ivPhoneGridListType.setOnClickListener(this);

        LinearLayout llSearchBy = (LinearLayout) view.findViewById(R.id.ll_phonegrid_searchby);
        llSearchBy.setOnClickListener(this);
        LinearLayout llSortBy = (LinearLayout) view.findViewById(R.id.ll_phonegrid_sortby);
        llSortBy.setOnClickListener(this);
    }

    private void showProductDetailView(ProductData product){

//        Bundle bundle = new Bundle();
//        bundle.putSerializable(Constants.PRODUCT_STRING,product);
//        bundle.putInt(Constants.CALL_FROM_INDEX, MainActivity.PHONE_VIEW); // ~screen 11
//
//        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
//        productDetailFragment.setArguments(bundle);
//
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frame_container, productDetailFragment).commit();

        application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadPhoneDetailTask(context,product.getId()).execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            case R.id.iv_phone_gridlist :
                Log.i(TAG, "Entered onItemSelected");
                updatePhoneView(paramView);
                break;
            case R.id.ll_phonegrid_searchby :
                Log.i(TAG, "Entered phone search by");
                searchByQuickAction.show(paramView);
                break;
            case R.id.ll_phonegrid_sortby :
                Log.i(TAG, "Entered phone sort by");

                break;
            default:
                return;

        }
    }

    private void updatePhoneView(View paramView){

        if (this.isGridShow){
            ivPhoneGridListType.setImageResource(R.drawable.ic_gridlist_menu_list);
            phoneGridView.setNumColumns(2);
            this.isGridShow = false;
        }
        else{
            ivPhoneGridListType.setImageResource(R.drawable.ic_gridlist_menu_grid);
            phoneGridView.setNumColumns(1);
            this.isGridShow = true;
        }
    }

    public void loadProductList(){
        application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
//            new LoadPhoneProductTask(phoneGridView.getContext(), productAdapter).execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }

    public void initQuickAction(){
        searchByQuickAction = new SearchByQuickAction(context);
        ActionItem text1Action = new ActionItem();
        text1Action.setTitle("text 1");
        searchByQuickAction.addActionItem(text1Action);

        ActionItem text2Action = new ActionItem();
        text2Action.setTitle("text 2");
        searchByQuickAction.addActionItem(text2Action);


    }
}
