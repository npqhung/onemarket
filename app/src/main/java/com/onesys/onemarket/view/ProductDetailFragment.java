package com.onesys.onemarket.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.image.ImageLoader;

public class ProductDetailFragment extends Fragment {

    ProductDetailData productDetail;
    private int callFromIndex;

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
        productDetail = (ProductDetailData) args
                .getSerializable(Constants.PRODUCT_STRING);
        callFromIndex = args.getInt(Constants.CALL_FROM_INDEX);

//        this.tv_info_rating.setText(paramProductDetailsData.getTotalRate() + " Ratings, " + paramProductDetailsData.getViewsCount() + " views");
//        this.rt_rating.setRating(paramProductDetailsData.getRatePointFloat());
//        this.imageLoader.DisplayImage(paramProductDetailsData.getFullThumbnail(), this.iv_sanpham_full, true);
//        this.tv_gia_sanpham.setText(paramProductDetailsData.getPrice(getActivity()));
//        this.tv_info_khuyen_mai.setText(paramProductDetailsData.getUuDai());
//        this.tv_info_con_hang.setText(paramProductDetailsData.getStatus(getActivity()));

        TextView tvName = (TextView) view
                .findViewById(R.id.tv_productdetail_name);
        tvName.setText(productDetail.getName());

        TextView tvCategory = (TextView) view
                .findViewById(R.id.tv_productdetail_category);
        tvCategory.setText(productDetail.getCategory_id());

        TextView tvInfoRating = (TextView) view
                .findViewById(R.id.tv_productdetail_info_rating);
        tvInfoRating.setText(productDetail.getTotal_rate() + " Ratings, " + productDetail.getViews_count() + " views");

        RatingBar productRating = (RatingBar)view.findViewById(R.id.rb_productdetail_rating);
        productRating.setRating(productDetail.getRatePointFloat());
        ImageLoader imgLoader = new ImageLoader(view.getContext());
        ImageView ivProductImg = (ImageView)view.findViewById(R.id.iv_productdetail_full);
        imgLoader.DisplayImage(productDetail.getThumbnail(), ivProductImg);
    }

}
