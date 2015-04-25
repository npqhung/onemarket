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
import com.onesys.onemarket.model.News;
import com.onesys.onemarket.service.NewsServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.NewsDetailResponse;

import com.onesys.onemarket.view.NewsDetailFragment;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadNewsDetailTask extends AsyncTask<String, Integer, NewsDetailResponse[]> {

    private static final String TAG = LoadNewsDetailTask.class.getName();
    private static final String newsDetailURL = Constants.DOMAIN + "/News/Detail?id=";
    private ProgressDialog progressDialog;
    private MainActivity context;
    private String newsId;

    public LoadNewsDetailTask(MainActivity context, String id) {
        this.context = context;
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");
        this.newsId = id;
    }

    protected NewsDetailResponse[] doInBackground(String... criteria) {

        Log.d(TAG, "Get Product Detail");

        NewsDetailResponse[] products = null;
        NewsServerManager server = new NewsServerManager(newsDetailURL);

        try {
            Log.d(this.getClass().getName(), "Get Product detail with ID : " + newsId);

            NewsDetailResponse response = server.getNewsDetail(newsId);
            products = new NewsDetailResponse[]{response};
        } catch (RuntimeException e) {
            Log.e(this.getClass().getName(), e.getMessage());
            return null;
        }

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
    protected void onPostExecute(NewsDetailResponse[] newsResponse) {
        dismissProgressDialog();
        super.onPostExecute(newsResponse);
        displayNewsDetailResult(newsResponse);
    }

    protected void displayNewsDetailResult(NewsDetailResponse[] newsResponse) {
        if (newsResponse == null || newsResponse[0].getData() == null) {
            dismissProgressDialog();
            Log.i(TAG, "Cannot get Product Detail");
        } else { //add to the list...
            dismissProgressDialog();
            News newsDetail = newsResponse[0].getData();
            showNewsDetail(newsDetail);

        }
    }

    private void dismissProgressDialog() {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    private void showProgressDialog() {
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    private void showNewsDetail(News newsDetail) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constants.NEWS_STRING, newsDetail);
        bundle.putInt(Constants.CALL_FROM_INDEX, MainActivity.NEWS_VIEW); // ~screen 11

        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        newsDetailFragment.setArguments(bundle);

        FragmentManager fragmentManager = context.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame_container, (Fragment) newsDetailFragment, "" + MainActivity.NEWS_DETAIL_VIEW);
        if (!context.mListScreen.contains(newsDetailFragment)) {
            context.mListScreen.add(newsDetailFragment);
        }

        context.hideAllFragment(fragmentTransaction);
        fragmentTransaction.show(newsDetailFragment);
        fragmentTransaction.commitAllowingStateLoss();

    }
}