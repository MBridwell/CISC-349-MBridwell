package com.example.hustagram;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GridViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gridview);

        GridView gridView = findViewById(R.id.grid_view); //


        ArrayList<ImageClass> imageList = new ArrayList<>();

        ImageAdapter adapter = new ImageAdapter(this, imageList);
        gridView.setAdapter(adapter);
    }
}
