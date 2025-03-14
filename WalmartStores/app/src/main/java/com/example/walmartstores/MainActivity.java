package com.example.walmartstores;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected static final String url = "https://nua.insufficient-light.com/data/walmart_store_locations.json";
    protected ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        list = (ListView)findViewById(R.id.walmart_list);
        ArrayList<WalmartStores> results = new ArrayList<WalmartStores>();

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response){
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String name = obj.getString("name");
                        String city = obj.getString("city");
                        String street_address = obj.getString("street_address");
                        String phone_number = obj.getString("phone_number");
                        String store_code = obj.getString("store_code");

                        WalmartStores store = new WalmartStores(name, city, street_address, phone_number, store_code);
                        results.add(store);
                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                }
                Adapter adapter = new Adapter(list.getContext(), results, queue);
                list.setAdapter(adapter);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Log.d("JSONArray Error", "Error:" + error);
            }
        });

        queue.add(jsonArrayRequest); // Corrected the typo here
    }
}
