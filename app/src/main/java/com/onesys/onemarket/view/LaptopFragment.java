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
import android.widget.Toast;

import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.task.LoadProductTask;
import com.onesys.onemarket.utils.Constants;

public class LaptopFragment extends Fragment implements View.OnClickListener{

    private OneMarketApplication application = null;
    private ProductAdapter productAdapter;

    private static final String TAG = "OneMarket";

    private boolean isGridShow = false;
    private ImageView ivLaptopGridListType;
    private GridView laptopGridView;

    public LaptopFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_laptop_gridview, container, false);

        productAdapter = new ProductAdapter(rootView.getContext());

        initLaptopGridView(rootView);

        return rootView;
    }

    public void onAttach(Activity paramActivity)
    {
        super.onAttach(paramActivity);
    }

    private void initLaptopGridView(View view) {
        laptopGridView = (GridView) view.findViewById(R.id.gv_laptop);
        laptopGridView.setNumColumns(2);

        loadProductList();

        laptopGridView.setAdapter(productAdapter);

        laptopGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //show product_detail view
                ProductData product = (ProductData) laptopGridView.getAdapter().getItem(position);
                showProductDetailView(product);
            }
        });

        this.ivLaptopGridListType = ((ImageView)view.findViewById(R.id.iv_laptop_gridlist));
        this.ivLaptopGridListType.setOnClickListener(this);
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
            case R.id.iv_laptop_gridlist :
                Log.i(TAG, "Entered onItemSelected");
                updatePhoneView(paramView);
                break;
            default:
                return;

        }
    }

    private void updatePhoneView(View paramView){

        if (this.isGridShow){
            ivLaptopGridListType.setImageResource(R.drawable.ic_gridlist_menu_list);
            laptopGridView.setNumColumns(2);
            this.isGridShow = false;
        }
        else{
            ivLaptopGridListType.setImageResource(R.drawable.ic_gridlist_menu_grid);
            laptopGridView.setNumColumns(1);
            this.isGridShow = true;
        }
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
