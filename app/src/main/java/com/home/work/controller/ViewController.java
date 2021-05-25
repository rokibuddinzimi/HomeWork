package com.home.work.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.home.work.Interface.OnDataAvailable;
import com.home.work.Interface.SuccessListener;
import com.home.work.Model.DataModel;
import com.home.work.NetworkManager.GetListOfPaymentMethods;
import com.home.work.R;
import com.home.work.view.PaymentMethodsListViewActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewController implements SuccessListener {
    private final String TAG = PaymentMethodsListViewActivity.class.getSimpleName();
    private Context context;
    private ProgressDialog pDialog;
    private OnDataAvailable onDataAvailable;

    public ViewController(Context context, OnDataAvailable onDataAvailable) {
        this.context = context;
        this.onDataAvailable = onDataAvailable;
    }

    private void showProgressDialog() {
        pDialog = new ProgressDialog(context);
        pDialog.show();
        pDialog.setMessage(context.getString(R.string.please_wait));
        pDialog.setCancelable(false);
    }

    public void startDataFetching() {
        showProgressDialog();
        new GetListOfPaymentMethods(this, "GET").execute();
    }

    @Override
    public void onSuccess(String jsonString) {
        ArrayList<DataModel> dataModels = new ArrayList<>();
        dataModels.clear();
        if (pDialog.isShowing())
            pDialog.dismiss();

        try {
            JSONObject jsonObj = new JSONObject(jsonString);
            JSONObject networkObject = jsonObj.getJSONObject("networks");
            JSONArray applicable = networkObject.getJSONArray("applicable");

            for (int i = 0; i < applicable.length(); i++) {
                JSONObject jObj = applicable.getJSONObject(i);
                String name = jObj.optString("label");

                JSONObject links = jObj.getJSONObject("links");
                String imageUrl = links.optString("logo");

                dataModels.add(new DataModel(name, imageUrl));
            }
        } catch (final JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
            onDataAvailable.onNoData(context.getString(R.string.try_again));
        }

        onDataAvailable.onAvailableData(dataModels);
    }

    @Override
    public void onFailed() {
        if (pDialog.isShowing())
            pDialog.dismiss();

        onDataAvailable.onNoData(context.getString(R.string.try_again));
    }
}
