package com.onesys.onemarket.task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.model.ProductData;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.ProductListResponse;

import java.util.ArrayList;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadProductTask extends AsyncTask<String, Integer, ProductListResponse[]> {

    private static final String TAG = LoadProductTask.class.getName();
    private static final String productListURL = "http://api.chodidong.me/index.php/Product/List";

    private ProductAdapter productAdapter;

    public LoadProductTask(ProductAdapter productAdapter) {
        this.productAdapter = productAdapter;
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
        //progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        // setProgressPercent(progress[0]);
        super.onProgressUpdate(progress);
    }

    @Override
    protected void onPostExecute(ProductListResponse[] productsResponse) {
        super.onPostExecute(productsResponse);
        displayProductListResult(productsResponse);
    }

    protected void displayProductListResult(ProductListResponse[] productsResponse) {
        if (productsResponse == null || productsResponse[0].getData() == null || productsResponse[0].getData().length == 0) {
//            progressBar.dismiss();
//            fuelOutletResponse = null;
//            EditText searchText = (EditText) findViewById(R.id.search_outlet_text);
//            //hide the bottom bar
//            showDialog("No outlets found for " + searchText.getText().toString() + ". Search surrounding areas?");
//            findViewById(R.id.footer).setVisibility(View.GONE);
            Log.i(TAG, "Cannot get Product List");
        } else { //add to the list...
//            progressBar.dismiss();
            //showDialog("Found " + outlets.length + " outlets " + " latitude is " + searchResult.getLatitude());

//            fuelOutletList = new OutletGeoLocationDisplayAdapter(this, new ArrayList<FuelOutlet>());
//            setListAdapter(fuelOutletList);
//            //update the list here by going through the result array
//            fuelOutletResponse = matchingOutlets[0];
//            FuelOutlet[] outlets = fuelOutletResponse.getOutlets();
//            for (int i = 0; i < outlets.length; i++) {
//                fuelOutletList.add(outlets[i]);
//            }

            //fuelOutletResponse = matchingOutlets[0];
            ProductData[] product = productsResponse[0].getData();
            ArrayList<ProductData> productList = new ArrayList<ProductData>();
            for (int i = 0; i < product.length; i++) {
                //fuelOutletList.add(outlets[i]);
                Log.i(TAG, product[i].getName() + " " + product[i].getPrice() );
                productList.add(product[i]);
            }

            productAdapter.addData(productList);

//    		fuelOutletList.notifyDataSetChanged(); //refresh the dataset...
        }
    }
}