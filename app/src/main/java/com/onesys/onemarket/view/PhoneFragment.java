package com.onesys.onemarket.view;

import android.app.Activity;
import android.app.Fragment;
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
import com.onesys.onemarket.task.LoadPhoneProductTask;
import com.onesys.onemarket.utils.quickaction.SortByActionItem;
import com.onesys.onemarket.utils.quickaction.SearchByQuickAction;
import com.onesys.onemarket.utils.quickaction.SortByQuickAction;

import org.apache.http.message.BasicNameValuePair;

import java.util.HashMap;
import java.util.LinkedList;

public class PhoneFragment extends Fragment implements View.OnClickListener{

    private OneMarketApplication application = null;
    private ProductAdapter productAdapter;

    private static final String TAG = "OneMarket";

    private boolean isGridShow = false;
    private ImageView ivPhoneGridListType;
    private GridView phoneGridView;
    private MainActivity context;

    private SearchByQuickAction searchByQuickAction;
    private SortByQuickAction sortByQuickAction;

    private final String CATEGORY_PHONE = "1";
    private final String PRICE_UP = "0";
    private final String PRICE_DOWN = "1";

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

        loadProductList(new LinkedList());

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
                sortByQuickAction.show(paramView);
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

    public void loadProductList(LinkedList criteria){
        application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadPhoneProductTask(phoneGridView.getContext(), productAdapter, criteria).execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }

    public void initQuickAction(){
        searchByQuickAction = new SearchByQuickAction(context);
        SortByActionItem text1Action = new SortByActionItem();
        text1Action.setTitle("text 1");
        searchByQuickAction.addActionItem(text1Action);

        SortByActionItem text2Action = new SortByActionItem();
        text2Action.setTitle("text 2");
        searchByQuickAction.addActionItem(text2Action);

        sortByQuickAction = new SortByQuickAction(context);

        SortByActionItem sort1Action = new SortByActionItem();
        sort1Action.setTitle("Most viewed");
        sortByQuickAction.addActionItem(sort1Action);
        SortByActionItem sort2Action = new SortByActionItem();
        sort2Action.setTitle("Price High to Low");
        sortByQuickAction.addActionItem(sort2Action);
        SortByActionItem sort3Action = new SortByActionItem();
        sort3Action.setTitle("Price Low to High");
        sortByQuickAction.addActionItem(sort3Action);

        final LinkedList criteria = new LinkedList();
        sortByQuickAction.setOnActionItemClickListener(new SortByQuickAction.OnActionItemClickListener(){
            public void onItemClick(int position){
                switch (position){
                    case 0:
                        Log.i(TAG, "Sort by View");
                        criteria.clear();
                        criteria.add(new BasicNameValuePair("category", CATEGORY_PHONE));
                        criteria.add(new BasicNameValuePair("sort", "views"));
                        loadProductList(criteria);
                        break;
                    case 1:
                        Log.i(TAG, "Sort by Price DOWN");
                        criteria.clear();
                        criteria.add(new BasicNameValuePair("category", CATEGORY_PHONE));
                        criteria.add(new BasicNameValuePair("price", PRICE_DOWN));
                        loadProductList(criteria);
                        break;
                    case 2:
                        Log.i(TAG, "Sort by PRICE UP");
                        criteria.clear();
                        criteria.add(new BasicNameValuePair("category", CATEGORY_PHONE));
                        criteria.add(new BasicNameValuePair("price", PRICE_UP));
                        loadProductList(criteria);
                        break;
                }
            }
        });
    }
}
