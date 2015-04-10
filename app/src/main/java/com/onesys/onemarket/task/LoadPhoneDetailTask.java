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
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.ProductDetailResponse;
import com.onesys.onemarket.view.ProductDetailFragment;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadPhoneDetailTask extends AsyncTask<String, Integer, ProductDetailResponse[]> {

    private static final String TAG = LoadPhoneDetailTask.class.getName();
    private static final String productDetailURL = Constants.DOMAIN + "/Product/Detail?id=";
    private ProgressDialog progressDialog;
    private MainActivity context;
    private String productId;

    public LoadPhoneDetailTask(MainActivity context, String id) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");
        this.productId = id;
    }

    protected ProductDetailResponse[] doInBackground(String... criteria) {

        Log.d(TAG, "Get Product Detail");

        ProductDetailResponse[] products = null;
        ServerManager server = new ServerManager(productDetailURL);

        //totalSize += Downloader.downloadFile(urls[i]);
        try {
            Log.d(this.getClass().getName(), "Get Product detail with ID : " + productId);

            ProductDetailResponse response = server.getProductDetail(productId);
//		             publishProgress((int) ((i / (float) count) * 100));
            //outlets = response.getOutlets(); //need to change this so the result is appended to the array
            products = new ProductDetailResponse[]{response};
        } catch (RuntimeException e) {
            Log.e(this.getClass().getName(), e.getMessage());
            return null;
        }
        // Escape early if cancel() is called
//	             if (isCancelled()) break;

        return products;
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
    protected void onPostExecute(ProductDetailResponse[] productsResponse) {
        dismissProgressDialog();
        super.onPostExecute(productsResponse);
        displayProductDetailResult(productsResponse);
    }

    protected void displayProductDetailResult(ProductDetailResponse[] productsResponse) {
        if (productsResponse == null || productsResponse[0].getData() == null ) {
            dismissProgressDialog();
            Log.i(TAG, "Cannot get Product Detail");
        } else { //add to the list...
            dismissProgressDialog();

            ProductDetailData productDetail = productsResponse[0].getData();
            showProductDetail(productDetail);

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

    private void showProductDetail(ProductDetailData productDetail){
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.PRODUCT_STRING,productDetail);
        bundle.putInt(Constants.CALL_FROM_INDEX, MainActivity.PHONE_VIEW); // ~screen 11

        ProductDetailFragment productDetailFragment = new ProductDetailFragment();
        productDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, (Fragment) productDetailFragment, "" + MainActivity.PRODUCT_DETAIL_VIEW);
        if (!context.mListScreen.contains(productDetailFragment)) {
            context.mListScreen.add(productDetailFragment);
        }

        context.hideAllFragment(fragmentTransaction);
        fragmentTransaction.show(productDetailFragment);
        fragmentTransaction.commitAllowingStateLoss();

    }
}