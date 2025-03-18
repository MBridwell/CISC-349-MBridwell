package com.example.walmartstores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<WalmartStores> arrayList;

    public Adapter(Context context, ArrayList<WalmartStores> arrayList, RequestQueue queue) {
        this.context = context;
        this.arrayList = arrayList;
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

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list, parent, false);
        }


        TextView name = convertView.findViewById(R.id.name);
        TextView city = convertView.findViewById(R.id.city);
        TextView phone_number = convertView.findViewById(R.id.phone_number);
        TextView street_address = convertView.findViewById(R.id.street_address);
        TextView store_code = convertView.findViewById(R.id.store_code);

        WalmartStores store = arrayList.get(position);

        name.setText(store.getName());
        city.setText(store.getCity());
        phone_number.setText(store.getPhone_number());
        street_address.setText(store.getStreet_address());
        store_code.setText(store.getStore_code());

        return convertView;
    }
}
