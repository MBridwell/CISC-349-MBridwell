package com.example.dynamiclist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ViewSongs extends AppCompatActivity {

    static HolidaySongsAdapter adapter;

    public static Intent newIntent(Context context, HolidaySongsAdapter holidaySongsAdapter) {
        Intent i = new Intent(context, ViewSongs.class);
        adapter = holidaySongsAdapter;
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mylist);
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }};

        getOnBackPressedDispatcher().addCallback(this, callback);

        int index = getIntent().getIntExtra(HolidaySongsAdapter.EXTRA_SELECTED_ITEM, -1);
        if (index >= 0){
            adapter.populateView(findViewById(R.id.album_layout), index);
        }




    }
}