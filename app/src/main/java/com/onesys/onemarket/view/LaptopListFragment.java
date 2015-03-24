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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.task.LoadProductTask;
import com.onesys.onemarket.utils.Constants;

public class LaptopListFragment extends Fragment implements View.OnClickListener{

    private OneMarketApplication application = null;
    private ProductAdapter productAdapter;

    private static final String TAG = "OneMarket";

    private boolean isGridShow = false;
    private ImageView ivGridListType;

    public LaptopListFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_laptop_listview, container, false);

        productAdapter = new ProductAdapter(rootView.getContext());

        initLaptopListView(rootView);

        return rootView;
    }

    public void onAttach(Activity paramActivity)
    {
        super.onAttach(paramActivity);
    }

    private void initLaptopListView(View view) {
        final ListView listView = (ListView) view.findViewById(R.id.lv_laptop);

        loadProductList();

        listView.setAdapter(productAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //show product_detail view
                ProductData product = (ProductData) listView.getAdapter().getItem(position);
                showProductDetailView(product);
            }
        });

        this.ivGridListType = ((ImageView)view.findViewById(R.id.iv_laptop_gridlist_grid));
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

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            case R.id.iv_laptop_gridlist_grid :
                Log.i(TAG, "Entered onItemSelected");
                showLaptopGridView();
                break;
            default:
                return;

        }
        if (this.isGridShow);

    }

    private void showLaptopGridView(){

//        PhoneGridFragment phoneGridFragment = new PhoneGridFragment();
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.frame_container, phoneGridFragment).commit();
    }

    public void loadProductList(){
        application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadProductTask(productAdapter).execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }
    }
}
