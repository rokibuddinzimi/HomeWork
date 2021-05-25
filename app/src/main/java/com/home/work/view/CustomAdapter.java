package com.home.work.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.home.work.Model.DataModel;
import com.home.work.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<DataModel> {

    private ArrayList<DataModel> dataSet;
    private Context mContext;

    private static class ViewHolder {
        TextView paymentMethodNameText;
        ImageView paymentMethodIconImage;
    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.payment_method_listview_row_item, data);
        this.dataSet = data;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        viewHolder = new ViewHolder();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.payment_method_listview_row_item, parent, false);
        viewHolder.paymentMethodNameText = convertView.findViewById(R.id.text_payment_method_name);
        viewHolder.paymentMethodIconImage = convertView.findViewById(R.id.image_payment_method);

        viewHolder.paymentMethodNameText.setText(dataModel.getName());
        Picasso.get().load(dataModel.getImageURL()).into(viewHolder.paymentMethodIconImage);

        return convertView;
    }
}