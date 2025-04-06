package com.example.hustagram;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.volley.Response;

import org.json.JSONArray;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> base64;


    public ImageAdapter(Context context, ArrayList<String> base64){
        this.context = context;
        this.base64 = base64;

    }


    @Override
    public int getCount() {
        return base64.size();
    }

    @Override
    public Object getItem(int position) {
        return base64.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.imagexml, parent, false);
        }

        String b64 = base64.get(position);
        ImageView image = convertView.findViewById(R.id.image_view);

        Bitmap decoded = decode(b64);

        image.setImageBitmap(decoded);


        return convertView;
    }
    private Bitmap decode(String b64){
        byte[] decodedString = Base64.decode(b64, Base64.DEFAULT);
        Bitmap decoded = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        return decoded;
    }

}
