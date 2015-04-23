package com.onesys.onemarket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.OnlinePayment;
import com.onesys.onemarket.task.LoadOnlinePayment;
import com.onesys.onemarket.utils.Constants;

/**
 * Created by Hung Nguyen on 4/19/2015.
 */

public class Webview extends Activity {
    private static final String RESPONSE_URL = Constants.DOMAIN + "/Order/StatusPayment?";
    public static String URL_STRING = "_load_url_thanh_toan";

    private void getResponse(String url) {
        OneMarketApplication application = (OneMarketApplication) getApplication();
        if (application.isOnline()) {
            new LoadOnlinePayment(this) {
                protected void onPostExecute(OnlinePayment onlinePayment) {
                    super.onPostExecute(onlinePayment);
                    Intent localIntent = new Intent();
                    localIntent.putExtra("status", onlinePayment.getStatus());
                    localIntent.putExtra("message", onlinePayment.getMessage());
                    setResult(-1, localIntent);
                    finish();
                }

            }.execute(new String[]{url});
        } else {
            Toast.makeText(this, " Network not available. Please check if you have enabled internet connectivity", Toast.LENGTH_LONG).show();
        }


//        new LoadGiaoDichInfo(this)
//        {
//            protected void onPostExecute(GiaoDichInfo paramAnonymousGiaoDichInfo)
//            {
//                super.onPostExecute(paramAnonymousGiaoDichInfo);
//                Intent localIntent = new Intent();
//                localIntent.putExtra("status", paramAnonymousGiaoDichInfo.getStatus());
//                localIntent.putExtra("message", paramAnonymousGiaoDichInfo.getMessage());
//                WebviewThanhtoan.this.setResult(-1, localIntent);
//                WebviewThanhtoan.this.finish();
//            }
//        }.execute(new String[] { paramString });
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.layout_webview_payment);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            setResult(0);
            finish();
            return;
        }
        String str = bundle.getString(URL_STRING);
        WebView localWebView = (WebView) findViewById(R.id.web_view_payment);
        localWebView.getSettings().setJavaScriptEnabled(true);
        localWebView.loadUrl(str);
        localWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString) {
                super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
            }

            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("shouldOverrideUrlLoading url = " + url);
                if (url.contains(RESPONSE_URL)) {
                    getResponse(url);
                    return true;
                }
                view.loadUrl(url);
                return false;
            }
        });
    }
}

