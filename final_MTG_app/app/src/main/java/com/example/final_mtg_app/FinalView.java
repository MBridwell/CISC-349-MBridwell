package com.example.final_mtg_app;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinalView extends AppCompatActivity {

    protected static final String url = "http://10.2.105.189:5000/get_card_info";
    protected ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.finalfrag);

        list = findViewById(R.id.card_list);

        ArrayList<String> cards = getIntent().getStringArrayListExtra("combined");
        Log.d("combined", cards.toString());
        if (cards != null) {
            Log.d("cards", cards.toString());
        } else {
            Log.d("cards", "Received null cards list");
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cards);


        list.setAdapter(adapter);


    }
}
