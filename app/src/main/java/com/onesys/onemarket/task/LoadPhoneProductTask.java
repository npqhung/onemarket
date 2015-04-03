package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.ProductListResponse;

import java.util.ArrayList;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadPhoneProductTask extends AsyncTask<String, Integer, ProductListResponse[]> {

    private static final String TAG = LoadPhoneProductTask.class.getName();
    private static final String productListURL = Constants.DOMAIN + "/Product/List?type=1";
    private ProgressDialog progressDialog;

    private ProductAdapter productAdapter;

    public LoadPhoneProductTask(Context context, ProductAdapter productAdapter) {
        this.productAdapter = productAdapter;

        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");
    }

    protected ProductListResponse[] doInBackground(String... criteria) {

        Log.d(TAG, "Get All Product");

        ProductListResponse[] products = null;
        ServerManager server = new ServerManager(productListURL);

        //totalSize += Downloader.downloadFile(urls[i]);
        try {
            Log.d(this.getClass().getName(), "calling remote server to find outlets");

            ProductListResponse response = server.getAllProductList();
//		             publishProgress((int) ((i / (float) count) * 100));
            //outlets = response.getOutlets(); //need to change this so the result is appended to the array
            products = new ProductListResponse[]{response};
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
    protected void onPostExecute(ProductListResponse[] productsResponse) {
        dismissProgressDialog();
        super.onPostExecute(productsResponse);
        displayProductListResult(productsResponse);
    }

    protected void displayProductListResult(ProductListResponse[] productsResponse) {
        if (productsResponse == null || productsResponse[0].getData() == null || productsResponse[0].getData().length == 0) {
            dismissProgressDialog();
            Log.i(TAG, "Cannot get Product List");
        } else { //add to the list...
            dismissProgressDialog();

            ProductData[] product = productsResponse[0].getData();
            ArrayList<ProductData> productList = new ArrayList<ProductData>();
            for (int i = 0; i < product.length; i++) {

                Log.i(TAG, product[i].getName() + " " + product[i].getPrice() );
                productList.add(product[i]);
            }

            productAdapter.addData(productList);
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
}