package com.onesys.onemarket.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.utils.Constants;

import java.util.ArrayList;

public class TabletFragment extends Fragment {
	
	public TabletFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_tablet, container, false);

        initListView(rootView);
        return rootView;
    }

    private void initListView(View view) {
        final ListView listView = (ListView) view.findViewById(R.id.lv_tablet);

        listView.setAdapter(new ProductAdapter(view.getContext(),buildProductList()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                //show product_detail view
                ProductData product = (ProductData)listView.getAdapter().getItem(position);
                showProductDetailView(product);
            }
        });
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
}
