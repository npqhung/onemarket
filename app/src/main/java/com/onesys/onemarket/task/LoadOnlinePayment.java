package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.OnlinePayment;
import com.onesys.onemarket.model.Payment;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.response.OnlinePaymentResponse;
import com.onesys.onemarket.utils.response.PaymentResponse;

/**
 * Created by Hung Nguyen on 4/19/2015.
 */

public class LoadOnlinePayment
        extends AsyncTask<String, Boolean, OnlinePayment>
{
    private Context mContext;
    private ProgressDialog progressDialog;

    public LoadOnlinePayment(Context paramContext)
    {
        this.mContext = paramContext;
        this.progressDialog = new ProgressDialog(this.mContext);
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage("Loading ...");
    }

    private void dismissProgressDialog()
    {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    private void showProgressDialog(){
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    protected OnlinePayment doInBackground(String... urls){
        String url = urls[0];
        OnlinePaymentResponse[] payment = null;
        ServerManager server = new ServerManager(url);

        try {
            Log.d(this.getClass().getName(), "calling remote server to get Payment");

            OnlinePaymentResponse response = server.getOnlinePayment();

            payment = new OnlinePaymentResponse[]{response};
        } catch (RuntimeException e) {
            Log.e(this.getClass().getName(), e.getMessage());
            return null;
        }
        // Escape early if cancel() is called
//	             if (isCancelled()) break;

        return payment[0].getData();
    }

    protected void onPostExecute(OnlinePayment onlinePayment)
    {
        dismissProgressDialog();
        super.onPostExecute(onlinePayment);
    }

    protected void onPreExecute()
    {
        showProgressDialog();
        super.onPreExecute();
    }
}

