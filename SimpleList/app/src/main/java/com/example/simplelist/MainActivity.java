package com.example.simplelist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;





public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ListView listView;
    TextView textView;
    String[] listItem;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);
        listItem = getResources().getStringArray(R.array.array_technology);

        for (String s : listItem) {
            Log.d(TAG, s);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_list,
                listItem);

        listView.setAdapter(adapter);

    }
}