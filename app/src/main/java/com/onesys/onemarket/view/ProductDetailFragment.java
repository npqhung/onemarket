package com.onesys.onemarket.view;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.model.SpecificationInfo;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.image.ImageLoader;

public class ProductDetailFragment extends Fragment implements View.OnClickListener{

    ProductDetailData productDetail;
    private int callFromIndex;
    private static final String TAG = "OneMarket";

    private LinearLayout detailView; //normal view
    private LinearLayout specView;
    private LinearLayout commentView;

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

        LinearLayout specBtn = (LinearLayout) view.findViewById(R.id.ll_specification);
        specBtn.setOnClickListener(this);
        LinearLayout commentBtn = (LinearLayout) view.findViewById(R.id.ll_usercomment);
        commentBtn.setOnClickListener(this);

        specView = (LinearLayout) view.findViewById(R.id.ll_productdetail_specification);
        commentView = (LinearLayout) view.findViewById(R.id.ll_productdetail_comment);
        detailView = (LinearLayout) view.findViewById(R.id.ll_productdetail_main);

        detailView.setVisibility(View.VISIBLE);

        SpecificationInfo mobileInfo = productDetail.getMobileinfo();
        parseSpecificationView(view, mobileInfo);

    }

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            case R.id.ll_specification :
                Log.i(TAG, "Entered Product Detail Specification");
                showSpecification(paramView);
                break;

            default:
                return;

        }
    }

    public void showSpecification(View view){
        detailView.setVisibility(View.GONE);
        commentView.setVisibility(View.GONE);
        specView.setVisibility(View.VISIBLE);
    }

    private void setTextInfo(View view, int paramInt, String paramString)
    {
        TextView localTextView = (TextView)view.findViewById(paramInt);
        if ((paramString == null) || (TextUtils.isEmpty(paramString)))
        {
            localTextView.setText("No Info");
            return;
        }
        localTextView.setText(Html.fromHtml(paramString).toString());
    }

    private void parseSpecificationView(View view, SpecificationInfo info)
    {
        setTextInfo(view, R.id.tv_spec_network_2g, info.getInfo_2g());
        setTextInfo(view, R.id.tv_spec_network_3g, info.getInfo_3g());
        setTextInfo(view, R.id.tv_spec_release_date, info.getInfo_date_present());//release date
        setTextInfo(view, R.id.tv_spec_sale_date, info.getInfo_date_sale());
//        setTextInfo(2131558543, info.getSize());
//        setTextInfo(2131558544, info.getWeight());
//        setTextInfo(2131558545, info.getSreenType());
//        this.mScreen.findViewById(2131558545).setSelected(true);
//        setTextInfo(2131558546, info.getSreenSize() + ", " + info.getSreenSizeWidthHeight());
//        setTextInfo(2131558547, info.getSreenMoreInfo());
//        setTextInfo(2131558548, info.getAudioType());
//        setTextInfo(2131558549, info.getAudioStandard());
//        setTextInfo(2131558550, info.getMemoryContact());
//        setTextInfo(2131558551, info.getMemoryCalled());
//        setTextInfo(2131558552, info.getMemoryHdd() + "," + info.getMemoryRam());
//        setTextInfo(2131558553, info.getMemorySlot());
//        setTextInfo(2131558554, info.getDataGprs());
//        setTextInfo(2131558555, info.getDataEdge());
//        setTextInfo(2131558556, info.getData3gSpeed());
//        setTextInfo(2131558557, info.getDataNfc());
//        setTextInfo(2131558558, info.getDataWlan());
//        setTextInfo(2131558559, info.getDataBluetooth());
//        setTextInfo(2131558560, info.getDataInfrared());
//        setTextInfo(2131558561, info.getDataUsb());
//        setTextInfo(2131558562, info.getCamera());
//        setTextInfo(2131558563, info.getCameraRecordVideo());
//        setTextInfo(2131558564, info.getCameraSub());
//        setTextInfo(2131558565, info.getOsDetail());
//        setTextInfo(2131558566, info.getCpu());
//        setTextInfo(2131558567, info.getChipset());
//        setTextInfo(2131558568, info.getSms());
//        setTextInfo(2131558569, info.getBrowser());
//        setTextInfo(2131558570, info.getRadio());
//        setTextInfo(2131558571, info.getGame());
//        setTextInfo(2131558572, null);
//        setTextInfo(2131558573, info.getLanguage());
//        setTextInfo(2131558574, info.getGps());
//        setTextInfo(2131558575, info.getJavaSupport());
//        setTextInfo(2131558576, info.getJavaMoreInfo());
//        setTextInfo(2131558577, info.getBattery());
//        setTextInfo(2131558578, info.getBatteryWaiting());
//        setTextInfo(2131558579, info.getBatteryCall());
    }

}
