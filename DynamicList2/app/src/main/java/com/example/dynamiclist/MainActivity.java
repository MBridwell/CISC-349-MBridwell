package com.example.dynamiclist;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
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
    protected static final String url = "https://nua.insufficient-light.com/data/holiday_songs_spotify.json";
    protected ListView list;


    //define DynamicList Elements

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.spotify_list);
        ArrayList<HolidaySongs> results = new ArrayList<HolidaySongs>();

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        String image = obj.getString("album_img");
                        String name = obj.getString("album_name");
                        String artist = obj.getString("artist_name");
                        Double dance = obj.getDouble("danceability");
                        Integer duration = obj.getInt("duration_ms");
                        String playlist_img = obj.getString("playlist_img");

                        HolidaySongs album = new HolidaySongs(image, name, artist, dance, duration, playlist_img);
                        results.add(album);
                    } catch (JSONException e) {
                        e.printStackTrace();}
                }
                HolidaySongsAdapter adapter = new HolidaySongsAdapter(list.getContext(), results, queue);
                list.setAdapter(adapter);
                list.setOnItemClickListener(adapter);
            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSONArray Error", "Error:" + error);
            }
        });// Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);



    }
}
// write function to call and parse jsondata
