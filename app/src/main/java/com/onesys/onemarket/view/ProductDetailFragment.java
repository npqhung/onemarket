package com.onesys.onemarket.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.utils.Constants;

public class ProductDetailFragment extends Fragment {

    ProductData productData;

    public ProductDetailFragment() {
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View view = inflater.inflate(R.layout.layout_item_product_detail, container, false);

        initProductDetailView(view);

        return view;
    }

    private void initProductDetailView(View view){
        Bundle args = getArguments();
        ProductData product = (ProductData) args
                .getSerializable(Constants.PRODUCT_STRING);

        // set value into textview
        TextView textView = (TextView) view
                .findViewById(R.id.detail_item_product_price);
        textView.setText(product.getPrice());

        // set image based on selected text
        ImageView imageView = (ImageView) view
                .findViewById(R.id.detail_item_product_image);

        imageView.setImageResource(product.getImageId());
    }

}
