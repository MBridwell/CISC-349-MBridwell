package com.example.final_mtg_app;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WelcomePage extends AppCompatActivity {

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
        Button startButton = findViewById(R.id.StartButton);
        //button for going to next page.
        ImageView randcard = findViewById(R.id.rand_card);
        //imageview for first random card.

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        String url = "http://10.2.97.180:5000/get_random";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Loop through each object in the response array
                        JSONObject obj = null;
                        try {
                            //make new object fill with data of first index (returning one json object, just use index 0)
                            obj = response.getJSONObject(0);
                            //store imageurl in image link
                            String image_link = obj.getString("image_url");
                            loadImage(image_link, randcard);

                        } catch (JSONException e) {
                            //log
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        Log.e("Volley", "Error: " + error.getMessage());
                    }
                });


        queue.add(jsonArrayRequest);





    }

    // Method to load an image from a URL into an ImageView
    private void loadImage(String imageUrl, ImageView imageView) {
        // Instantiate the ImageLoader
        ImageLoader imageLoader = new ImageLoader(Volley.newRequestQueue(this), new ImageLoader.ImageCache() {
            @Override
            public android.graphics.Bitmap getBitmap(String url) {
                return null;
            }

            @Override
            public void putBitmap(String url, android.graphics.Bitmap bitmap) {
            }
        });

        // Load the image into the ImageView using the ImageLoader
        imageLoader.get(imageUrl, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                if (response.getBitmap() != null) {
                    // Set the image on the ImageView if the response contains a valid Bitmap
                    imageView.setImageBitmap(response.getBitmap());
                }
            }

            @Override
            public void onErrorResponse(com.android.volley.VolleyError error) {
                // Handle error response
                Log.e("Volley", "Error loading image: " + error.getMessage());
            }
        });
    }
}