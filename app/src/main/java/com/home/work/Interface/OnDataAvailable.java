package com.home.work.Interface;

import com.home.work.Model.DataModel;

import java.util.ArrayList;

public interface OnDataAvailable {
    public void onAvailableData(ArrayList<DataModel> dataModels);
    public void onNoData(String errorText);
}