package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.R;
import com.onesys.onemarket.model.ProductDetailData;
import com.onesys.onemarket.model.TabletDetail;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.TabletDetailResponse;
import com.onesys.onemarket.view.ProductDetailFragment;
import com.onesys.onemarket.view.TabletDetailFragment;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadTabletDetailTask extends AsyncTask<String, Integer, TabletDetailResponse[]> {

    private static final String TAG = LoadTabletDetailTask.class.getName();
    private static final String tabletDetailURL = Constants.DOMAIN + "/Product/Detail?id=";
    private ProgressDialog progressDialog;
    private MainActivity context;
    private String productId;

    public LoadTabletDetailTask(MainActivity context, String id) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");
        this.productId = id;
    }

    protected TabletDetailResponse[] doInBackground(String... criteria) {

        Log.d(TAG, "Get Product Detail");

        TabletDetailResponse[] tablets = null;
        ServerManager server = new ServerManager(tabletDetailURL);

        try {
            Log.d(this.getClass().getName(), "Get Product detail with ID : " + productId);

            TabletDetailResponse response = server.getTabletDetail(productId);

            tablets = new TabletDetailResponse[]{response};
        } catch (RuntimeException e) {
            Log.e(this.getClass().getName(), e.getMessage());
            return null;
        }
        // Escape early if cancel() is called
//	             if (isCancelled()) break;

        return tablets;
    }

    @Override
    protected void onPreExecute() {
        showProgressDialog();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // setProgressPercent(progress[0]);
        super.onProgressUpdate(progress);
    }

    @Override
    protected void onPostExecute(TabletDetailResponse[] productsResponse) {
        dismissProgressDialog();
        super.onPostExecute(productsResponse);
        displayTabletDetailResult(productsResponse);
    }

    protected void displayTabletDetailResult(TabletDetailResponse[] tabletsResponse) {
        if (tabletsResponse == null || tabletsResponse[0].getData() == null ) {
            dismissProgressDialog();
            Log.i(TAG, "Cannot get Product Detail");
        } else { //add to the list...
            dismissProgressDialog();

            TabletDetail productDetail = tabletsResponse[0].getData();
            showTabletDetail(productDetail);

        }
    }
    private void dismissProgressDialog()    {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    private void showProgressDialog()    {
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    private void showTabletDetail(TabletDetail tabletDetail){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PRODUCT_STRING,tabletDetail);
        bundle.putInt(Constants.CALL_FROM_INDEX, MainActivity.TABLET_VIEW);

        TabletDetailFragment tabletDetailFragment = new TabletDetailFragment();
        tabletDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, (Fragment) tabletDetailFragment, "" + MainActivity.PRODUCT_DETAIL_VIEW);
        if (!context.mListScreen.contains(tabletDetailFragment)) {
            context.mListScreen.add(tabletDetailFragment);
        }

        context.hideAllFragment(fragmentTransaction);
        fragmentTransaction.show(tabletDetailFragment);
        fragmentTransaction.commitAllowingStateLoss();

    }
}