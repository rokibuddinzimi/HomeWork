package com.home.work.NetworkManager;

import android.os.AsyncTask;
import android.util.Log;

import com.home.work.Interface.SuccessListener;
import com.home.work.NetworkManager.HttpHandler;
import com.home.work.Utility.APIEndPoints;
import com.home.work.view.PaymentMethodsListViewActivity;

public class GetListOfPaymentMethods extends AsyncTask<Void, Void, Void> {
    private final String TAG = PaymentMethodsListViewActivity.class.getSimpleName();
    private SuccessListener mSuccessListener;
    private String mRequestType;

    public GetListOfPaymentMethods(SuccessListener successListener, String requestType) {
        this.mSuccessListener = successListener;
        mRequestType = requestType;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();
        // Making a request to url and getting response
        String jsonString = sh.makeGetServiceCall(APIEndPoints.getDataURL(), mRequestType);
        Log.e(TAG, "Response from url: " + jsonString);
        if (jsonString != null) {
            mSuccessListener.onSuccess(jsonString);
        } else {
            Log.e(TAG, "Couldn't get json from server.");
            mSuccessListener.onFailed();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
