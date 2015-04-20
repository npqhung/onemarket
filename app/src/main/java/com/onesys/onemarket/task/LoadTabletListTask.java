package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.adapter.TabletAdapter;
import com.onesys.onemarket.model.Tablet;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.Constants;
import com.onesys.onemarket.utils.response.TabletListResponse;

import org.apache.http.client.utils.URLEncodedUtils;

import java.util.ArrayList;
import java.util.LinkedList;

public class LoadTabletListTask extends AsyncTask<String, Integer, TabletListResponse[]> {

    private static final String TAG = LoadTabletListTask.class.getName();
    private static final String tabletListURL = Constants.DOMAIN + "/Product/List?category=2";
    private ProgressDialog progressDialog;

    private TabletAdapter tabletAdapter;
    private LinkedList criteria;
    private MainActivity context;

    public LoadTabletListTask(Context context, TabletAdapter tabletAdapter, LinkedList criteria) {
        this.tabletAdapter = tabletAdapter;
        this.criteria = criteria;

        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");

        this.context = (MainActivity)context;
    }

    protected TabletListResponse[] doInBackground(String... param) {

        Log.d(TAG, "Get All Tablet");

        TabletListResponse[] tablets = null;

        String andStr ="";
        if(tabletListURL.indexOf("&") == -1){
            andStr += "&";
        }

        ServerManager server = new ServerManager(tabletListURL + andStr + URLEncodedUtils.format(criteria, "utf-8"));

        try {
            Log.d(this.getClass().getName(), "calling remote server to get Tablet list");

            TabletListResponse response = server.getAllTabletList();

            tablets = new TabletListResponse[]{response};
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
    protected void onPostExecute(TabletListResponse[] tabletsResponse) {
        dismissProgressDialog();
        super.onPostExecute(tabletsResponse);
        displayTabletListResult(tabletsResponse);
    }

    protected void displayTabletListResult(TabletListResponse[] tabletsResponse) {
        if (tabletsResponse == null || tabletsResponse[0].getData() == null || tabletsResponse[0].getData().length == 0) {
            dismissProgressDialog();
            Log.i(TAG, "Cannot get Tablet List");
            Toast.makeText(context, " Cannot load Tablet List", Toast.LENGTH_LONG).show();
        } else { //add to the list...
            dismissProgressDialog();

            Tablet[] tablet = tabletsResponse[0].getData();
            ArrayList<Tablet> tabletList = new ArrayList<Tablet>();
            for (int i = 0; i < tablet.length; i++) {

                Log.i(TAG, tablet[i].getName() + " " + tablet[i].getPrice() );
                tabletList.add(tablet[i]);
            }
            Log.i(TAG, "Size : " + tabletList.size());

            tabletAdapter.addData(tabletList);
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