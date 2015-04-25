package com.onesys.onemarket.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.dialog.StoreDialog;
import com.onesys.onemarket.model.News;
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.model.ProductStore;
import com.onesys.onemarket.model.SpecificationInfo;
import com.onesys.onemarket.task.LoadPhoneCommentTask;
import com.onesys.onemarket.utils.BaseFragment;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.image.ImageLoader;

import java.io.Serializable;

public class NewsDetailFragment extends BaseFragment implements Serializable, View.OnClickListener {

    private static final String TAG = "OneMarket";

    public NewsDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_news_detail_item, container, false);

        initNewsDetailView(view);

        return view;
    }

    private void initNewsDetailView(View view) {
        Bundle args = getArguments();
        News news = (News) args
                .getSerializable(Constants.NEWS_STRING);
//        callFromIndex = args.getInt(Constants.CALL_FROM_INDEX);
        WebView wvTitle = (WebView) view.findViewById(R.id.wv_newsdetail_tittle);
        TextView tvTime = (TextView) view.findViewById(R.id.tv_newsdetail_time);
        wvTitle.loadDataWithBaseURL("x-data://base", news.getTitle(), "text/html", "UTF-8", null);
        tvTime.setText(news.getCreatedDate());
        WebView wvContent = (WebView) view.findViewById(R.id.wv_newsdetail_content);
        wvContent.loadDataWithBaseURL("x-data://base", Html.fromHtml(news.getContent()).toString(), "text/html", "UTF-8", null);
        wvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString) {
                paramAnonymousWebView.loadUrl(paramAnonymousString);
                return false;
            }
        });
    }

    public void onClick(View paramView) {
        switch (paramView.getId()) {
            case R.id.iv_productdetail_back:
                Log.i(TAG, "Entered Product Detail Specification");
                showBack();
                break;

            default:
                return;

        }
    }

    private void showBack() {

    }
}
