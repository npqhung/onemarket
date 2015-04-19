package com.onesys.onemarket.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.onesys.onemarket.MainActivity;
import com.onesys.onemarket.application.OneMarketApplication;
import com.onesys.onemarket.model.Payment;
import com.onesys.onemarket.service.ServerManager;
import com.onesys.onemarket.utils.response.PaymentResponse;

/**
 * Created by Hung Nguyen on 4/19/2015.
 */

public class LoadCashPayment
        extends AsyncTask<String, Boolean, Payment>
{
    private Context mContext;
    private ProgressDialog progressDialog;

    public LoadCashPayment(Context paramContext)
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

    protected Payment doInBackground(String... urls){
        String url = urls[0];
        PaymentResponse[] payment = null;
        ServerManager server = new ServerManager(url);

        try {
            Log.d(this.getClass().getName(), "calling remote server to get Payment");

            PaymentResponse response = server.getCashPayment();

            payment = new PaymentResponse[]{response};
        } catch (RuntimeException e) {
            Log.e(this.getClass().getName(), e.getMessage());
            return null;
        }
        // Escape early if cancel() is called
//	             if (isCancelled()) break;

        return payment[0].getData();
    }

    protected void onPostExecute(Payment payment)
    {
        dismissProgressDialog();
        super.onPostExecute(payment);

        if (payment != null){
            ((OneMarketApplication)((MainActivity)mContext).getApplication()).clearDataCart();
            ((MainActivity) mContext).mFootMenu.updateCartCount();
            Toast.makeText(mContext, "Payment successfully", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(mContext, "Transaction is cancelled", Toast.LENGTH_SHORT).show();
        }

        ((MainActivity) mContext).showContent(MainActivity.CART_VIEW);
    }

    protected void onPreExecute()
    {
        showProgressDialog();
        super.onPreExecute();
    }
}