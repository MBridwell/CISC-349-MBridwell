package com.example.walmartstores;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;


public class Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<WalmartStores> arrayList;
    private RequestQueue queue;
    private TextView name, city, street_address, phone_number, store_code;



    public Adapter (Context context, ArrayList<WalmartStores> arrayList, RequestQueue queue){
        this.context = context;
        this.arrayList = arrayList;
        this.queue = queue;
    }



    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list,parent,false);
        name = convertView.findViewById(R.id.name);
        city = convertView.findViewById(R.id.city);
        phone_number = convertView.findViewById(R.id.phone_number);
        street_address = convertView.findViewById(R.id.street_address);
        store_code = convertView.findViewById(R.id.store_code);

        return convertView;
    }


}
