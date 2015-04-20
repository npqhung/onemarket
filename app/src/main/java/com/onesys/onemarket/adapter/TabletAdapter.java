package com.onesys.onemarket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.model.Tablet;
import com.onesys.onemarket.utils.image.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Hung on 12/03/2015.
 */
public class TabletAdapter extends BaseAdapter {
    private MainActivity context;

    private ArrayList<Tablet> productList;

    public ArrayList<Tablet> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Tablet> productList) {
        this.productList = productList;
    }

    public TabletAdapter(MainActivity c){
        context = c;
    }

    public TabletAdapter(MainActivity context, ArrayList<Tablet> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        if(productList == null) {
            return 0;
        }

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

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View detailView;

//        if (convertView == null) {

            detailView = new View(context);
            Tablet product = productList.get(position);

            // get detail layout
            detailView = inflater.inflate(R.layout.layout_gridlist_tablet_detail_grid, null);

            ImageView localImageView = (ImageView)detailView.findViewById(R.id.iv_tablet_image);
            TextView tvProductName = (TextView)detailView.findViewById(R.id.tv_tablet_name);
            TextView tvProductCategory = (TextView)detailView.findViewById(R.id.tv_tablet_category);
            TextView tvProductPrice = (TextView)detailView.findViewById(R.id.tv_tablet_price);
            RatingBar productRating = (RatingBar)detailView.findViewById(R.id.rb_tablet_rating);
            TextView tvProductRatingTotal = (TextView)detailView.findViewById(R.id.tv_tablet_rating_total);

            ImageLoader imgLoader = new ImageLoader(detailView.getContext());
            imgLoader.DisplayImage(product.getThumbnail(), localImageView);
            tvProductName.setText(product.getName());
//            tvProductCategory.setText(this.mContext.getString(2131099706) + " -" + product.getUuDai() + "%");
            tvProductCategory.setText("Discount - " + product.afterDiscount() + "%");
            tvProductPrice.setText(product.getPriceFormat());
//            tvProductPrice.setText(product.getPrice());
            productRating.setRating(product.getRatePointFloat());
            tvProductRatingTotal.setText("(" + product.getTotalRate() + ")");

//        } else {
//            detailView = (View) convertView;
//        }

        return detailView;
    }

    public void clearData()
    {
        if (this.productList == null)
            this.productList = new ArrayList();
        this.productList.clear();
        notifyDataSetChanged();
    }

    public void addData(ArrayList<Tablet> productList)
    {
        if (this.productList == null)
            this.productList = new ArrayList();

        this.productList.addAll(productList);
        notifyDataSetChanged();
    }
}
