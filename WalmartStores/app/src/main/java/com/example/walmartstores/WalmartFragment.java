package com.example.walmartstores;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

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

public class WalmartFragment extends Fragment {

    protected static final String URL = "https://nua.insufficient-light.com/data/walmart_store_locations.json";
    protected ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment, container, false);


        list = rootView.findViewById(R.id.walmart_list);


        fetchData();

        return rootView;
    }

    private void fetchData() {

        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<WalmartStores> results = new ArrayList<>();

                        Log.d("WalmartFragment", "Response: " + response.toString()); // Log the entire response

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject obj = response.getJSONObject(i);
                                String name = obj.getString("name");
                                String city = obj.getString("city");
                                String street_address = obj.getString("street_address");
                                String phone_number = obj.getString("phone_number_1");
                                String store_code = obj.getString("index");

                                WalmartStores store = new WalmartStores(name, city, street_address, phone_number, store_code);
                                results.add(store);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        Log.d("WalmartFragment", "Parsed Results: " + results.size() + " stores"); // Log how many stores were parsed


                        if (!results.isEmpty()) {
                            Adapter adapter = new Adapter(getContext(), results, queue);
                            list.setAdapter(adapter);
                        } else {
                            Log.d("WalmartFragment", "No stores available to display");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSONArray Error", "Error: " + error.getMessage());
                    }
                });

        queue.add(jsonArrayRequest);
    }
}