package com.example.hustagram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ImageClass> arrayList;

    public ImageAdapter(Context context, ArrayList<ImageClass> arrayList){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.gridview, parent, false);
        }


        ImageClass imageClass = arrayList.get(position);


        ImageView image = convertView.findViewById(R.id.imageContainer);
        TextView comment = convertView.findViewById(R.id.commentBox);
        TextView date = convertView.findViewById(R.id.dateBox);


        image.setImageBitmap(imageClass.getPicture());
        comment.setText(imageClass.getComment());
        date.setText(imageClass.getDate());

        return convertView;
    }

}
