package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;

import com.onesys.onemarket.adapter.NewsAdapter;
import com.onesys.onemarket.adapter.ProductAdapter;
import com.onesys.onemarket.model.News;
import com.onesys.onemarket.service.NewsServerManager;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.NewsListResponse;
import com.onesys.onemarket.utils.response.ProductListResponse;


import org.apache.http.client.utils.URLEncodedUtils;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Hung Nguyen on 3/22/2015.
 */
public class LoadNewsListTask extends AsyncTask<String, Integer, NewsListResponse[]> {

    private static final String TAG = LoadNewsListTask.class.getName();
    private static final String newsListURL = Constants.DOMAIN + "/News/List";
    private ProgressDialog progressDialog;

    private NewsAdapter newsAdapter;
    private MainActivity context;

    public LoadNewsListTask(Context context, NewsAdapter newsAdapter, LinkedList criteria) {
        this.newsAdapter = newsAdapter;

        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");

        this.context = (MainActivity) context;
    }

    protected NewsListResponse[] doInBackground(String... param) {

        Log.d(TAG, "Get All Product");

        NewsListResponse[] products = null;
        NewsServerManager server = new NewsServerManager(newsListURL);

        try {
            Log.d(this.getClass().getName(), "calling remote server to get News list");

            NewsListResponse response = server.getAllNewsList();

            products = new NewsListResponse[]{response};
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
    protected void onPostExecute(NewsListResponse[] newsResponse) {
        dismissProgressDialog();
        super.onPostExecute(newsResponse);
        displayProductListResult(newsResponse);
    }

    protected void displayProductListResult(NewsListResponse[] newsResponse) {
        if (newsResponse == null || newsResponse[0].getData() == null || newsResponse[0].getData().length == 0) {
            dismissProgressDialog();
            Log.i(TAG, "Cannot get news List");
            Toast.makeText(context, " Cannot load news List", Toast.LENGTH_LONG).show();
        } else { //add to the list...
            dismissProgressDialog();

            News[] news = newsResponse[0].getData();
            ArrayList<News> newsList = new ArrayList<News>();
            for (int i = 0; i < news.length; i++) {
                newsList.add(news[i]);
                Log.i(TAG, "News Title === : " + news[i].getTitle());
            }
            Log.i(TAG, "Size : " + newsList.size());

            newsAdapter.addData(newsList);
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
}