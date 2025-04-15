package com.example.final_mtg_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.volley.RequestQueue;

import java.util.List;

public class CardAdapter extends BaseAdapter {

    private Context context;
    private List<cardClass> cards;
    private RequestQueue queue;

    public CardAdapter(Context context, List<cardClass> cards, RequestQueue queue) {
        this.context = context;
        this.cards = cards;
        this.queue = queue;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position; // You can return unique ID if you have one in cardClass
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // You’ll want to inflate your custom layout here and bind card data
        // For now, just return null or a placeholder until you build the layout
        return null;
    }
}