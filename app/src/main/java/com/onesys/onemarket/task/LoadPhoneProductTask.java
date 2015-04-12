package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.ProductListResponse;

import org.apache.http.client.utils.URLEncodedUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadPhoneProductTask extends AsyncTask<String, Integer, ProductListResponse[]> {

    private static final String TAG = LoadPhoneProductTask.class.getName();
    private static final String productListURL = Constants.DOMAIN + "/Product/List?";
    private ProgressDialog progressDialog;

    private ProductAdapter productAdapter;
    private LinkedList criteria;
    private MainActivity context;

    public LoadPhoneProductTask(Context context, ProductAdapter productAdapter, LinkedList criteria) {
        this.productAdapter = productAdapter;
        this.criteria = criteria;

        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");

        this.context = (MainActivity)context;
    }

    protected ProductListResponse[] doInBackground(String... param) {

        Log.d(TAG, "Get All Product");

        ProductListResponse[] products = null;
        ServerManager server = new ServerManager(productListURL + URLEncodedUtils.format(criteria, "utf-8"));

        try {
            Log.d(this.getClass().getName(), "calling remote server to get Product list");

            ProductListResponse response = server.getAllProductList();

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
            Toast.makeText(context, " Cannot load Product List", Toast.LENGTH_LONG).show();
        } else { //add to the list...
            dismissProgressDialog();

            ProductData[] product = productsResponse[0].getData();
            ArrayList<ProductData> productList = new ArrayList<ProductData>();
            for (int i = 0; i < product.length; i++) {

                Log.i(TAG, product[i].getName() + " " + product[i].getPrice() );
                productList.add(product[i]);
            }
            Log.i(TAG, "Size : " + productList.size());

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