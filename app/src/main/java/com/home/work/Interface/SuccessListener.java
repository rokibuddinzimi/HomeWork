package com.home.work.Interface;

import com.home.work.Model.DataModel;

import java.util.ArrayList;

public interface SuccessListener {
    //public void onSuccess(ArrayList<DataModel> dataModels);
    public void onSuccess(String jsonString);
    public void onFailed();
}