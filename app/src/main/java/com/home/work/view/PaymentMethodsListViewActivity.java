package com.home.work.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.home.work.Interface.OnDataAvailable;
import com.home.work.Model.DataModel;
import com.home.work.R;
import com.home.work.Utility.Utils;
import com.home.work.controller.ViewController;

import java.util.ArrayList;

public class PaymentMethodsListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OnDataAvailable {
    private ListView listView;
    private ProgressDialog pDialog;
    private Utils utils = new Utils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        initNetworkCall();
    }

    private void initNetworkCall() {
        //showProgressDialog();
        if (utils.isNetworkAvailable(this)) {
            new ViewController(this, this).startDataFetching();
            //new GetListOfPaymentMethods(this, "GET").execute();

        } else {
            utils.showDialog(this, getString(R.string.error), getString(R.string.check_connectivity));
        }
    }

/*    private void showProgressDialog(){
        pDialog = new ProgressDialog(this);
        pDialog.show();
        pDialog.setMessage(getString(R.string.please_wait));
        pDialog.setCancelable(false);
    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //TODO implement listview onclick listener
    }

    @Override
    public void onAvailableData(ArrayList<DataModel> dataModels) {
        CustomAdapter adapter;
        adapter = new CustomAdapter(dataModels, getApplicationContext());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listView.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onNoData(String errorText) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Utils().showDialog(PaymentMethodsListViewActivity.this, getString(R.string.error), errorText);
            }
        });
    }

/*    @Override
    public void onSuccess(ArrayList<DataModel> dataModels) {
        if (pDialog.isShowing())
            pDialog.dismiss();

        CustomAdapter adapter;
        adapter = new CustomAdapter(dataModels, getApplicationContext());
        listView.setAdapter(adapter);
    }

    @Override
    public void onFailed() {
        if (pDialog.isShowing())
            pDialog.dismiss();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new Utils().showDialog(PaymentMethodsListViewActivity.this, getString(R.string.error), getString(R.string.try_again));
            }
        });
    }*/
}