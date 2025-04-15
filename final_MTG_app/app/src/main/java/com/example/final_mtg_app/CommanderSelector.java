package com.example.final_mtg_app;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommanderSelector extends AppCompatActivity {
    //url String
    String url = "http://10.2.105.189:5000/get_commander";

    NetworkImageView commander_viewer;

    int cacheSize = 4 * 1024 * 1024; // 4MiB

    cardClass savedcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.commanderselector);
        //integrate csv to search for a legendary creature.
        //once found, store it to local var, and pass that data to scryfall api to find the image of said card
        //display image
        //reroll onclick
        //search button and edit text functionality.
        commander_viewer = findViewById(R.id.commander_image_viewer);
        jsonrequest();

        EditText textbox = findViewById(R.id.commander_search);

        Button rerollbutton = findViewById(R.id.reroll_button);
        Button search = findViewById(R.id.search_button);
        Button submit = findViewById(R.id.submit_button);

        rerollbutton.setOnClickListener(v ->{
            jsonrequest();
        });

        search.setOnClickListener(v -> {
            String tosearch = textbox.getText().toString();
            Log.d("Search term", tosearch);
           search(textbox.getText().toString());

        });

        submit.setOnClickListener(v ->{
            Intent intent = new Intent(CommanderSelector.this, CardTypePage.class);
            intent.putExtra("name", savedcard.getCardName());
            intent.putExtra("Commander_color", savedcard.getCardColor().toArray(new String[0]));
            Log.d("CommanderColor", Arrays.toString(savedcard.getCardColor().toArray(new String[0])));

            intent.putExtra("cmc", savedcard.getCMC());
            intent.putExtra("image_url", savedcard.getCardImage());
            intent.putExtra("Legendary Creature . . .", savedcard.getCardType());
            startActivity(intent);
        });

    }
    private void jsonrequest(){

        //function to reload page on reroll

        //create a new requestqueue
        RequestQueue queue = Volley.newRequestQueue(this);
        //start the queue
        queue.start();


        //get JSONArray of random Legendary Creature
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject commander = response.getJSONObject(0); // Since you only get one
                            String name = commander.getString("name");
                            Log.d("Name of card", name);

                            String encodedName = Uri.encode(name);
                            String scryfall_image_lookup = "https://api.scryfall.com/cards/named?exact=" + encodedName;

                            String cmc = commander.getString("cmc");
                            Log.d("Converted Mana Cost", cmc);

                            JSONArray colorArray = commander.getJSONArray("color_identity");
                            List<String> color = new ArrayList<>();
                            for (int i = 0; i < colorArray.length(); i++) {
                                color.add(colorArray.getString(i));
                            }



                            String type = commander.getString("type");
                            Log.d("type", type);

                            cardClass savedcard = new cardClass(color, cmc, type, name, scryfall_image_lookup);

                            CommanderSelector.this.savedcard = savedcard;



                            //encode and retrieve image url:
                            JsonObjectRequest imageRequest = new JsonObjectRequest(
                                    Request.Method.GET,
                                    scryfall_image_lookup,
                                    null,
                                    imageResponse -> {
                                        try {
                                            // Fetch the "image_uris" object from the response
                                            JSONObject imageUris = imageResponse.getJSONObject("image_uris");

                                            // Get the "normal" image URL
                                            String imageUrl = imageUris.getString("normal");

                                            // Set the image URL to NetworkImageView
                                            commander_viewer.setImageUrl(imageUrl, imageload(queue));
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


                            queue.add(imageRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );

        queue.add(jsonArrayRequest);
    }


    private void search(String name){
        RequestQueue searchqueue = Volley.newRequestQueue(this);
        searchqueue.start();


        String encodedName = Uri.encode(name);
        String scryfall_image_lookup = "https://api.scryfall.com/cards/named?exact=" + encodedName;


        JsonObjectRequest imageRequest = new JsonObjectRequest(
                Request.Method.GET,
                scryfall_image_lookup,
                null,
                imageResponse -> {
                    try {
                        // Fetch the "image_uris" object from the response
                        JSONObject imageUris = imageResponse.getJSONObject("image_uris");

                        // Get the "normal" image URL
                        String imageUrl = imageUris.getString("normal");

                        // Set the image URL to NetworkImageView
                        commander_viewer.setImageUrl(imageUrl, imageload(searchqueue));
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
        searchqueue.add(imageRequest);

    }

    private ImageLoader imageload(RequestQueue queue) {


        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(cacheSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
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


        return new ImageLoader(queue, imageCache);
    }


}



