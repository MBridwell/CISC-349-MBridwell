package com.example.final_mtg_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class WelcomePage extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.welcome_page);
        //this page is welcome page
        //have an image of a random card
        //display it, add animation maybe
        //welcome text
        //start button


        //button for going to next page.
        Button startButton = findViewById(R.id.StartButton);



        //imageview for first random card.
        NetworkImageView randcard = findViewById(R.id.rand_card);

        //create a new requestqueue
        RequestQueue queue = Volley.newRequestQueue(this);


        //start the queue
        queue.start();

        //utilize scryfall api to get random card
        String url = "https://api.scryfall.com/cards/random";


        //startbutton onclick
        startButton.setOnClickListener( v  -> {
            //create new intent to pass to next page
            Intent intent = new Intent(WelcomePage.this, CommanderSelector.class);
            //start the activity
            startActivity(intent);
        });


        //generic imageloader object
        int cacheSize = 4 * 1024 * 1024; // 4MiB
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache(){

            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(cacheSize){
                @Override
                protected int sizeOf(String key, Bitmap value){
                    return value.getByteCount();
                }
            };

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        };

        ImageLoader imageLoader = new ImageLoader(queue, imageCache);



        //get image string url from mongodb
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                response -> {
                    try {
                        // Fetch the "image_uris" object from the response
                        JSONObject imageUris = response.getJSONObject("image_uris");

                        // Get the "normal" image URL
                        String imageUrl = imageUris.getString("normal");

                        // Set the image URL to NetworkImageView
                        randcard.setImageUrl(imageUrl, imageLoader);
                        Log.d("image_url_scryfall", imageUrl);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Scryfall Error", "Failed to parse JSON response: " + e.getMessage());
                    }
                },
                error -> {
                    Log.e("volley", "Scryfall request failed: " + error.toString());
                }
        );


        queue.add(request);
    }
}