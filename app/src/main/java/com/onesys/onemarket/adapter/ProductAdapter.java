package com.onesys.onemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductData;

import java.util.ArrayList;

/**
 * Created by Hung on 12/03/2015.
 */
public class ProductAdapter extends BaseAdapter {
    private Context context;

    // Keep all Images in array
//    public Integer[] mThumbIds = {
//            R.drawable.temp_phone1, R.drawable.temp_phone2,
//            R.drawable.temp_phone3, R.drawable.temp_phone4,
//            R.drawable.temp_phone5, R.drawable.temp_phone6
//    };

    private ArrayList<ProductData> productList;

    public ArrayList<ProductData> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<ProductData> productList) {
        this.productList = productList;
    }

    // Constructor
    public ProductAdapter(Context c, ArrayList<ProductData> productList){
        context = c;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView = new ImageView(mContext);
//        imageView.setImageResource(mThumbIds[position]);
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
//        return imageView;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get detail layout
            gridView = inflater.inflate(R.layout.layout_gridlist_product_detail, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.gridlist_item_product_price);
            textView.setText(productList.get(position).getPrice());

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.gridlist_item_product_image);

            imageView.setImageResource(productList.get(position).getImageId());
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setLayoutParams(new GridView.LayoutParams(250, 250));
            ViewGroup.LayoutParams lp = imageView.getLayoutParams();
            lp.width = 120;
            lp.height = 120;
            imageView.requestLayout();

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
