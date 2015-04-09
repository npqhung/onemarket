package com.onesys.onemarket.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.adapter.CommentAdapter;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.dialog.StoreDialog;
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.model.ProductStore;
import com.onesys.onemarket.model.SpecificationInfo;
import com.onesys.onemarket.task.LoadPhoneCommentTask;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.image.ImageLoader;

import java.io.Serializable;

public class ProductDetailFragment extends Fragment implements Serializable, View.OnClickListener, StoreDialog.StoreListener{

    ProductDetailData productDetail;
    private int callFromIndex;
    private static final String TAG = "OneMarket";

    private LinearLayout detailView; //normal view
    private LinearLayout specView;
    private LinearLayout commentView;

    private CommentAdapter commentAdapter;
    private int currentTab; //0 : main; 1 : spec; 2 : user comments

    public ProductDetailFragment() {
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View view = inflater.inflate(R.layout.layout_item_product_detail, container, false);

        initProductDetailView(view);

        //init comment view
        commentAdapter = new CommentAdapter((MainActivity)getActivity());
        ListView commentView = (ListView) view.findViewById(R.id.lv_comment);
        commentView.setAdapter(commentAdapter);

        return view;
    }

    private void initProductDetailView(View view){
        Bundle args = getArguments();
        productDetail = (ProductDetailData) args
                .getSerializable(Constants.PRODUCT_STRING);
        callFromIndex = args.getInt(Constants.CALL_FROM_INDEX);

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

        ImageView backBtn = ((ImageView)view.findViewById(R.id.iv_productdetail_back));
        backBtn.setOnClickListener(this);

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

        LinearLayout addToCartBtn = (LinearLayout)view.findViewById(R.id.ll_payment);
        addToCartBtn.setOnClickListener(this);

    }

    public void onClick(View paramView)
    {
        switch (paramView.getId())
        {
            case R.id.iv_productdetail_back :
                Log.i(TAG, "Entered Product Detail Specification");
                showBack();
                break;
            case R.id.ll_specification :
                Log.i(TAG, "Entered Product Detail Specification");
                showSpecification(paramView);
                break;
            case R.id.ll_usercomment :
                Log.i(TAG, "Entered Product Detail Specification");
                showComments(paramView);
                break;
            case R.id.ll_payment :
                Log.i(TAG, "Entered Add To Cart");
                if (productDetail.getProduct_store() == null || productDetail.getProduct_store().size() == 0){
                    Toast.makeText(getActivity(), getString(R.string.str_empty_store), Toast.LENGTH_SHORT).show();
                } else{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constants.PRODUCT_STRING,productDetail);
                    bundle.putSerializable(Constants.STORE_LISTENER, this);

                    StoreDialog storeDialog = new StoreDialog();
                    storeDialog.setArguments(bundle);

                    storeDialog.show(getActivity().getFragmentManager(), "Dialog store fragment");
                }

                break;

            default:
                return;

        }
    }

    private void showBack(){
        if (this.currentTab != 0)
        {
            showDetailView();
            return;
        }

        Log.i(TAG,"Show Phone Fragment");
        FragmentManager fragmentManager = getActivity().getFragmentManager();
        Fragment phoneFragment = fragmentManager.findFragmentByTag("" + MainActivity.PHONE_VIEW);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ((MainActivity)getActivity()).hideAllFragment(fragmentTransaction);
        fragmentTransaction.show(phoneFragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void showDetailView(){
        if (this.currentTab == 0) {
            return;
        }
        this.currentTab = 0;

        detailView.setVisibility(View.VISIBLE);
        commentView.setVisibility(View.GONE);
        specView.setVisibility(View.GONE);
    }

    private void showComments(View view){

        if (this.currentTab == 2) {
            return;
        }
        this.currentTab = 2;

        OneMarketApplication application = (OneMarketApplication) getActivity().getApplication();
        if (application.isOnline()) {
            new LoadPhoneCommentTask((MainActivity)getActivity(),productDetail.getId(),commentAdapter).execute();
        } else {
            Toast.makeText(this.getActivity(), " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }

        detailView.setVisibility(View.GONE);
        commentView.setVisibility(View.VISIBLE);
        specView.setVisibility(View.GONE);
    }

    private void showSpecification(View view){
        if (this.currentTab == 1) {
            return;
        }
        this.currentTab = 1;

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
        setTextInfo(view, R.id.tv_spec_size, info.getSize());
        setTextInfo(view, R.id.tv_spec_weight, info.getWeight());
//        setTextInfo(view, R.id.tv_spec_screen_type, info.getSreen_type());
//        this.mScreen.findViewById(2131558545).setSelected(true);
        setTextInfo(view, R.id.tv_spec_size, info.getSreen_size() + ", " + info.getSreen_size_width_height());
//        setTextInfo(view, R.id.tv_spec_, info.getSreenMoreInfo());
        setTextInfo(view, R.id.tv_spec_ring_type, info.getAudio_type());
        setTextInfo(view,R.id.tv_spec_audio_output, info.getAudio_standard());
        setTextInfo(view,R.id.tv_spec_contact, info.getMemory_contact());
        setTextInfo(view, R.id.tv_spec_called_memory, info.getMemory_called());
        setTextInfo(view, R.id.tv_spec_internal_ram, info.getMemory_hdd() + "," + info.getMemory_ram());
        setTextInfo(view,R.id.tv_spec_memory_slot, info.getMemory_slot());
        setTextInfo(view, R.id.tv_spec_gprs, info.getData_gprs());
        setTextInfo(view, R.id.tv_spec_edge, info.getData_edge());
        setTextInfo(view, R.id.tv_spec_speed_3g, info.getData_3g_speed());
        setTextInfo(view,R.id.tv_spec_nfc, info.getData_nfc());
        setTextInfo(view, R.id.tv_spec_wlan, info.getData_wlan());
        setTextInfo(view,R.id.tv_spec_bluetooth, info.getData_bluetooth());
        setTextInfo(view, R.id.tv_spec_infrared, info.getData_infrared());
        setTextInfo(view,R.id.tv_spec_usb, info.getData_usb());
        setTextInfo(view,R.id.tv_spec_rear_camera, info.getCamera());
        setTextInfo(view, R.id.tv_spec_camera_recorder, info.getCamera_record_video());
        setTextInfo(view,R.id.tv_spec_front_camera, info.getCamera_sub());
        setTextInfo(view,R.id.tv_spec_os, info.getOs_detail());
        setTextInfo(view,R.id.tv_spec_cpu, info.getCpu());
        setTextInfo(view, R.id.tv_spec_chipset, info.getChipset());
        setTextInfo(view, R.id.tv_spec_sms, info.getSms());
        setTextInfo(view,R.id.tv_spec_browser, info.getBrowser());
        setTextInfo(view,R.id.tv_spec_radio, info.getRadio());
        setTextInfo(view, R.id.tv_spec_game, info.getGame());
//        setTextInfo(2131558572, null);
//        setTextInfo(2131558573, info.getLanguage());
//        setTextInfo(2131558574, info.getGps());
//        setTextInfo(2131558575, info.getJavaSupport());
//        setTextInfo(2131558576, info.getJavaMoreInfo());
//        setTextInfo(2131558577, info.getBattery());
//        setTextInfo(2131558578, info.getBatteryWaiting());
//        setTextInfo(2131558579, info.getBatteryCall());
    }

    public void onChooseStore(ProductStore productStore)
    {
        String storeId = productStore.store_id;
        System.out.println("storeInfo.store_id = " + productStore.store_id);
//        addToCart(storeId);
    }

//    private void addToCart(final String storeId)
//    {
//        if (TextUtils.isEmpty(((OneMarketApplication)getActivity().getApplication()).getUserId()))
//        {
//            Toast.makeText(getActivity(), "Please login to complete it!", 0).show();
//            return;
//        }
//        new CheckAddTask(getActivity(), paramString, this.productDetailsData)
//        {
//            protected void onPostExecute(Boolean paramAnonymousBoolean)
//            {
//                super.onPostExecute(paramAnonymousBoolean);
//                if (paramAnonymousBoolean.booleanValue())
//                {
//                    ((MobileMarketApplication)DS4DienThoaiDetailMoreFragment.this.getActivity().getApplication()).addDataCart(DS4DienThoaiDetailMoreFragment.this.productDetailsData, paramString);
//                    Toast.makeText(DS4DienThoaiDetailMoreFragment.this.getActivity(), "This product will be added to cart!", 0).show();
//                    ((MobileMarketActivity)DS4DienThoaiDetailMoreFragment.this.getActivity()).updateGioHangTab();
//                    return;
//                }
//                Toast.makeText(DS4DienThoaiDetailMoreFragment.this.getActivity(), "This product can't purchased now!", 0).show();
//            }
//        }
//                .execute(new String[0]);
//    }

}
