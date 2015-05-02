package com.onesys.onemarket.view;

import android.app.Activity;
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
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.task.LoadPhoneDetailTask;
import com.onesys.onemarket.task.LoadSearchTask;
import com.onesys.onemarket.utils.BaseFragment;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.SearchByUtils;
import com.onesys.onemarket.utils.quickaction.SearchByActionItem;
import com.onesys.onemarket.utils.quickaction.SearchByQuickAction;
import com.onesys.onemarket.utils.quickaction.SortByActionItem;
import com.onesys.onemarket.utils.quickaction.SortByQuickAction;
import com.onesys.onemarket.utils.response.ProductListResponse;

import org.apache.http.message.BasicNameValuePair;

import java.util.LinkedList;

public class SearchResultFragment extends BaseFragment implements View.OnClickListener {

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
    private LinkedList criteria;

    public SearchResultFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_search_result, container, false);
        context = (MainActivity) getActivity();

        productAdapter = new ProductAdapter(context);
        pageIndex = 1;

        Bundle args = getArguments();
        criteria = (LinkedList) args.getSerializable(Constants.KEY_SEARCH_CRITERIA);

        initPhoneGridView(rootView);

        return rootView;
    }

    public void onAttach(Activity paramActivity) {
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

        loadSearchList(criteria);

        this.ivPhoneGridListType = ((ImageView) view.findViewById(R.id.iv_phone_gridlist));
        this.ivPhoneGridListType.setOnClickListener(this);

    }

    private void showProductDetailView(ProductData product) {

        application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadPhoneDetailTask(context, product.getId()).execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.iv_phone_gridlist:
                Log.i(TAG, "Entered onItemSelected");
                updatePhoneView(paramView);
                break;
            case R.id.ll_phonegrid_searchby:
                Log.i(TAG, "Entered phone search by");
                searchByQuickAction.show(paramView);
                break;
            case R.id.ll_phonegrid_sortby:
                Log.i(TAG, "Entered phone sort by");
                sortByQuickAction.show(paramView);
                break;
            default:
                return;

        }
    }

    private void updatePhoneView(View paramView) {

        if (this.isGridShow) {
            ivPhoneGridListType.setImageResource(R.drawable.ic_gridlist_menu_list);
            phoneGridView.setNumColumns(2);
            this.isGridShow = false;
        } else {
            ivPhoneGridListType.setImageResource(R.drawable.ic_gridlist_menu_grid);
            phoneGridView.setNumColumns(1);
            this.isGridShow = true;
        }
    }

    public void loadSearchList(LinkedList criteria) {
        application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadSearchTask(context, productAdapter, criteria) {
            }.execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }
}
