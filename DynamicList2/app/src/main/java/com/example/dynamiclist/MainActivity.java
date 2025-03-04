package com.example.dynamiclist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView List;
    String tempdata[] = {
            "LOL",
            "XD",
            ":3",
            "TROLLFACE"
    };



    //define DynamicList Elements

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        List = findViewById(R.id.spotify_list);
        ArrayAdapter<String> arr;
        arr = new ArrayAdapter<>(this, R.layout.mylist, tempdata);
        List.setAdapter(arr);


    }
}
// write function to call and parse jsondata
