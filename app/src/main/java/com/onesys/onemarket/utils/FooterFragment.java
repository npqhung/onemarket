package com.onesys.onemarket.utils;

/**
 * Created by Hung on 11/03/2015.
 */

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;

public class FooterFragment extends BaseFragment
{
    private LinearLayout mFooterGioHang;
//    private TextView mFooterGioHangCount;
//    private LinearLayout mFooterKhuyenMai;
//    private LinearLayout mFooterThem;
    private LinearLayout mFooterTimKiem;

    private LinearLayout mFooterTrangChu;
    private ImageView mIvGioHang;
//    private ImageView mIvKhuyenMai;
//    private ImageView mIvThem;
    private ImageView mIvTimKiem;

    private ImageView mIvTrangChu;
    private BaseFragment.ShowContentListener mShowContentListener;

    private TextView mTxtGioHang;
//    private TextView mTxtKhuyenMai;
//    private TextView mTxtThem;
    private TextView mTxtTimKiem;
    private TextView mTxtTrangChu;

    private void initControl(View paramView)
    {
        this.mFooterTrangChu = ((LinearLayout)paramView.findViewById(R.id.layout_footer_home));
        this.mFooterTimKiem = ((LinearLayout)paramView.findViewById(R.id.layout_footer_search));
        this.mFooterGioHang = ((LinearLayout)paramView.findViewById(R.id.layout_footer_cart));
//        this.mFooterKhuyenMai = ((LinearLayout)paramView.findViewById(2131558659));
//        this.mFooterThem = ((LinearLayout)paramView.findViewById(2131558662));

        this.mTxtTrangChu = ((TextView)paramView.findViewById(R.id.tv_footer_home));

        this.mTxtTimKiem = ((TextView)paramView.findViewById(R.id.tv_footer_search));
        this.mTxtGioHang = ((TextView)paramView.findViewById(R.id.tv_footer_cart));
//        this.mTxtKhuyenMai = ((TextView)paramView.findViewById(2131558661));
//        this.mTxtThem = ((TextView)paramView.findViewById(2131558664));
//        this.mFooterGioHangCount = ((TextView)paramView.findViewById(2131558658));
//        ((MobileMarketActivity)getActivity()).updateGioHangTab();
        this.mIvTrangChu = ((ImageView)paramView.findViewById(R.id.iv_footer_home));
        this.mIvTimKiem = ((ImageView)paramView.findViewById(R.id.iv_footer_search));

        this.mIvGioHang = ((ImageView)paramView.findViewById(R.id.iv_footer_cart));
//        this.mIvKhuyenMai = ((ImageView)paramView.findViewById(2131558660));
//        this.mIvThem = ((ImageView)paramView.findViewById(2131558663));
        setViewSelect(this.mFooterTrangChu, this.mTxtTrangChu, this.mIvTrangChu, true);
        setViewSelect(this.mFooterTimKiem, this.mTxtTimKiem, this.mIvTimKiem, false);
        setViewSelect(this.mFooterGioHang, this.mTxtGioHang, this.mIvGioHang, false);
//        setViewSelect(this.mFooterKhuyenMai, this.mTxtKhuyenMai, this.mIvKhuyenMai, false);
//        setViewSelect(this.mFooterThem, this.mTxtThem, this.mIvThem, false);
        this.mFooterTrangChu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                FooterFragment.this.showHome();
            }
        });
        this.mFooterTimKiem.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterTrangChu, FooterFragment.this.mTxtTrangChu, FooterFragment.this.mIvTrangChu, false);
                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterTimKiem, FooterFragment.this.mTxtTimKiem, FooterFragment.this.mIvTimKiem, true);
                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterGioHang, FooterFragment.this.mTxtGioHang, FooterFragment.this.mIvGioHang, false);
//                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterKhuyenMai, FooterFragment.this.mTxtKhuyenMai, FooterFragment.this.mIvKhuyenMai, false);
//                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterThem, FooterFragment.this.mTxtThem, FooterFragment.this.mIvThem, false);
                FooterFragment.this.mShowContentListener.showContent(MainActivity.SEARCH_VIEW);
            }
        });
        this.mFooterGioHang.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterTrangChu, FooterFragment.this.mTxtTrangChu, FooterFragment.this.mIvTrangChu, false);
                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterTimKiem, FooterFragment.this.mTxtTimKiem, FooterFragment.this.mIvTimKiem, false);
                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterGioHang, FooterFragment.this.mTxtGioHang, FooterFragment.this.mIvGioHang, true);
//                FooterFragment.this.setViewSelect(FooterFragment.this.mFooterKhuyenMai, FooterFragment.this.mTxtKhuyenMai, FooterPart.this.mIvKhuyenMai, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterThem, FooterPart.this.mTxtThem, FooterPart.this.mIvThem, false);
                FooterFragment.this.mShowContentListener.showContent(MainActivity.CART_VIEW);

            }
        });
//        this.mFooterKhuyenMai.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View paramAnonymousView)
//            {
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterTrangChu, FooterPart.this.mTxtTrangChu, FooterPart.this.mIvTrangChu, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterTimKiem, FooterPart.this.mTxtTimKiem, FooterPart.this.mIvTimKiem, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterGioHang, FooterPart.this.mTxtGioHang, FooterPart.this.mIvGioHang, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterKhuyenMai, FooterPart.this.mTxtKhuyenMai, FooterPart.this.mIvKhuyenMai, true);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterThem, FooterPart.this.mTxtThem, FooterPart.this.mIvThem, false);
//                FooterPart.this.mShowContentListener.showContent(3);
//            }
//        });
//        this.mFooterThem.setOnClickListener(new View.OnClickListener()
//        {
//            public void onClick(View paramAnonymousView)
//            {
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterTrangChu, FooterPart.this.mTxtTrangChu, FooterPart.this.mIvTrangChu, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterTimKiem, FooterPart.this.mTxtTimKiem, FooterPart.this.mIvTimKiem, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterGioHang, FooterPart.this.mTxtGioHang, FooterPart.this.mIvGioHang, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterKhuyenMai, FooterPart.this.mTxtKhuyenMai, FooterPart.this.mIvKhuyenMai, false);
//                FooterPart.this.setViewSelect(FooterPart.this.mFooterThem, FooterPart.this.mTxtThem, FooterPart.this.mIvThem, true);
//                FooterPart.this.mShowContentListener.showContent(4);
//            }
//        });
    }

    private void setViewSelect(LinearLayout paramLinearLayout, TextView paramTextView, ImageView paramImageView, boolean paramBoolean)
    {
        paramLinearLayout.setSelected(paramBoolean);
        paramImageView.setSelected(paramBoolean);
        Resources localResources = getResources();
    }

    public void onAttach(Activity paramActivity)
    {
        super.onAttach(paramActivity);
        this.mShowContentListener = ((BaseFragment.ShowContentListener)paramActivity);
    }

    public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
    {
        View localView = paramLayoutInflater.inflate(R.layout.layout_footer, paramViewGroup, false);
        initControl(localView);
        return localView;
    }

//    public void setGioHangCount(int paramInt)
//    {
//        this.mFooterGioHangCount.setText(paramInt);
//        if (paramInt == 0)
//        {
//            this.mFooterGioHangCount.setVisibility(8);
//            return;
//        }
//        this.mFooterGioHangCount.setVisibility(0);
//    }

    public void showHome()
    {
        this.mTxtTrangChu.setSelected(true);
        setViewSelect(this.mFooterTrangChu, this.mTxtTrangChu, this.mIvTrangChu, true);
        setViewSelect(this.mFooterTimKiem, this.mTxtTimKiem, this.mIvTimKiem, false);
        setViewSelect(this.mFooterGioHang, this.mTxtGioHang, this.mIvGioHang, false);
//        setViewSelect(this.mFooterKhuyenMai, this.mTxtKhuyenMai, this.mIvKhuyenMai, false);
//        setViewSelect(this.mFooterThem, this.mTxtThem, this.mIvThem, false);
        this.mShowContentListener.showContent(MainActivity.HOME_VIEW);
    }
}