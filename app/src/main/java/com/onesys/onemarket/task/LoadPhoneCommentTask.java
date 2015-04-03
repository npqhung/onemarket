package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.adapter.CommentAdapter;
import com.onesys.onemarket.model.ProductComment;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.ProductCommentResponse;

import java.util.ArrayList;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadPhoneCommentTask extends AsyncTask<String, Integer, ProductCommentResponse[]> {

    private static final String TAG = LoadPhoneCommentTask.class.getName();
    private static final String productCommentURL = Constants.DOMAIN + "/Product/GetComments?product_id=";
    private ProgressDialog progressDialog;
    private MainActivity context;
    private String productId;
    private CommentAdapter commentAdapter;

    public LoadPhoneCommentTask(MainActivity context, String id, CommentAdapter commentAdapter) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");
        this.productId = id;
        this.commentAdapter = commentAdapter;
    }

    protected ProductCommentResponse[] doInBackground(String... criteria) {

        Log.d(TAG, "Get Product Detail");

        ProductCommentResponse[] products = null;
        ServerManager server = new ServerManager(productCommentURL);

        //totalSize += Downloader.downloadFile(urls[i]);
        try {
            Log.d(this.getClass().getName(), "Get Product detail with ID : " + productId);

            ProductCommentResponse response = server.getProductComment(productId);
//		             publishProgress((int) ((i / (float) count) * 100));
            //outlets = response.getOutlets(); //need to change this so the result is appended to the array
            products = new ProductCommentResponse[]{response};
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
    protected void onPostExecute(ProductCommentResponse[] commentResponses) {
        dismissProgressDialog();
        super.onPostExecute(commentResponses);
        displayProductComment(commentResponses);
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

    public void displayProductComment(ProductCommentResponse[] commentsResponse){
        if (commentsResponse == null || commentsResponse[0].getData() == null || commentsResponse[0].getData().length == 0) {
            dismissProgressDialog();
            Log.i(TAG, "Cannot get Product List");
        } else { //add to the list...
            dismissProgressDialog();

            ProductComment[] comments = commentsResponse[0].getData();

            ArrayList<ProductComment> commentList = new ArrayList<ProductComment>();
            for (int i = 0; i < comments.length; i++) {

                Log.i(TAG, comments[i].getFullname() + " " + comments[i].getContent());
                commentList.add(comments[i]);
            }

            commentAdapter.addData(commentList);

        }
    }
}