package com.onesys.onemarket.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import com.onesys.onemarket.utils.SearchByUtils;
import com.onesys.onemarket.utils.quickaction.SearchByActionItem;
import com.onesys.onemarket.utils.quickaction.SortByActionItem;
import com.onesys.onemarket.utils.quickaction.SearchByQuickAction;
import com.onesys.onemarket.utils.quickaction.SortByQuickAction;
import com.onesys.onemarket.utils.response.ProductListResponse;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
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

    private int pageIndex;
    private boolean isLoading = false;
    private boolean isHasMoreData = false;
    private LinkedList criteria = new LinkedList();

    public PhoneFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_phone_gridview, container, false);
        context = (MainActivity)getActivity();

        productAdapter = new ProductAdapter(context);
        pageIndex = 1;

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

        productAdapter.clearData();

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

        phoneGridView.setOnScrollListener(new AbsListView.OnScrollListener()
        {
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,int totalItemCount)
            {

                if ((firstVisibleItem + visibleItemCount == totalItemCount)&& (!isLoading) && (isHasMoreData))
                {
                    pageIndex++;

                    Log.i(TAG,"firstVisibleItem : " +  firstVisibleItem
                                    + "\n" + " visibleItemCount : " + visibleItemCount
                                    + "\n" + " totalItemCount : " + totalItemCount
                                    + "\n" + " isLoading : " + isLoading
                                    + "\n" + " isHasMoreData : " + isHasMoreData
                                    + "\n" + " pageIndex : " + pageIndex
                    );

                    loadDataMore();
                }
            }

            public void onScrollStateChanged(AbsListView view, int scrollState) {}
        });

        loadProductList(criteria);

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
            new LoadPhoneProductTask(context,  productAdapter, criteria){
                protected ProductListResponse[] doInBackground(String... paramAnonymousVarArgs)
                {
                    isLoading = true;
                    return super.doInBackground(paramAnonymousVarArgs);
                }

                protected void onPostExecute(ProductListResponse[] productsResponse)
                {
                    isLoading = false;
                    isHasMoreData = true;
                    super.onPostExecute(productsResponse);


                }
            }.execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }

    public void initQuickAction(){

        searchByQuickAction = new SearchByQuickAction(context);

        SearchByActionItem priceItem = new SearchByActionItem(0,
                getActivity().getResources().getStringArray(R.array.array_quick_action_searchby)[0], SearchByUtils.getMobileOptionList(0));
        searchByQuickAction.addActionItem(priceItem);

        SearchByActionItem brandItem = new SearchByActionItem(1,
                getActivity().getResources().getStringArray(R.array.array_quick_action_searchby)[1], SearchByUtils.getMobileOptionList(1));
        searchByQuickAction.addActionItem(brandItem);

        SearchByActionItem osItem = new SearchByActionItem(2,
                getActivity().getResources().getStringArray(R.array.array_quick_action_searchby)[2], SearchByUtils.getMobileOptionList(2));
        searchByQuickAction.addActionItem(osItem);

        SearchByActionItem screenItem = new SearchByActionItem(3,
                getActivity().getResources().getStringArray(R.array.array_quick_action_searchby)[3], SearchByUtils.getMobileOptionList(3));
        searchByQuickAction.addActionItem(screenItem);

        SearchByActionItem featureItem = new SearchByActionItem(4,
                getActivity().getResources().getStringArray(R.array.array_quick_action_searchby)[4], SearchByUtils.getMobileOptionList(4));
        searchByQuickAction.addActionItem(featureItem);

        SearchByActionItem promotionItem = new SearchByActionItem(5,
                getActivity().getResources().getStringArray(R.array.array_quick_action_searchby)[5], SearchByUtils.getMobileOptionList(5));
        searchByQuickAction.addActionItem(promotionItem);

        searchByQuickAction.setOnActionItemClickListener(new SearchByQuickAction.OnActionItemClickListener() {
            public void onItemAcceptClick(String selectedPrice, String selectedBrand, String selectedOS, String selectedScreen, String selectedFeature, String selectedPromotion) {
//                ((MobileMarketActivity)DS4DienThoaiFragment.this.getActivity()).showFrameBlack(false);
//                DS4DienThoaiFragment.this.sort = null;
//                DS4DienThoaiFragment.this.price = null;
//                DS4DienThoaiFragment.this.brand = paramAnonymousString2;
//                DS4DienThoaiFragment.this.status = paramAnonymousString6;
//                DS4DienThoaiFragment.this.key_search = null;
//                DS4DienThoaiFragment.this.price_search = paramAnonymousString1;
//                DS4DienThoaiFragment.this.promotion = null;
//                DS4DienThoaiFragment.this.pagesize = null;
//                DS4DienThoaiFragment.this.pageIndex = 1;
//                DS4DienThoaiFragment.this.loadData();
                criteria.clear();
                criteria.add(new BasicNameValuePair("category", CATEGORY_PHONE));
                if (!selectedBrand.equals("-1")) {
                    criteria.add(new BasicNameValuePair("brand", selectedBrand));
                }
                if (!selectedPrice.equals("-1")) {
                    criteria.add(new BasicNameValuePair("price_search", selectedPrice));
                }

                if (!selectedPromotion.equals("-1")) {
                    criteria.add(new BasicNameValuePair("status", selectedPromotion));
                }
                loadProductList(criteria);
            }
        });

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

    private void loadDataMore(){
        Log.i(TAG,"Load MORE DATA");
        criteria.add(new BasicNameValuePair("pageIndex", ""+pageIndex));
        loadProductList(criteria);
    }
}
