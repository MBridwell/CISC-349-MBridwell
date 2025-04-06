package com.example.hustagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);
        ArrayList<String> listImage = new ArrayList<>();

        //get backbutton from toolbar


        final TextView commentBox = (TextView) findViewById(R.id.commentBox);
        final TextView dateBox = (TextView) findViewById(R.id.dateBox);
        String date_to_find = getIntent().getStringExtra("date");

        ImageView backbutton = findViewById(R.id.arrowBack);

        String comment = getIntent().getStringExtra("comment");
        String date = getIntent().getStringExtra("date");
        commentBox.setText(comment);
        dateBox.setText(date);
        String url = "http://10.2.105.189:5000/get_data";


        RequestQueue queue = Volley.newRequestQueue(this);
        queue.start();

        backbutton.setOnClickListener(v -> {
            Intent intent = new Intent(ViewImageActivity.this, GridViewActivity.class);
            startActivity(intent);
        });


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Loop through each object in the response array
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Get each object (image) from the array
                                JSONObject obj = response.getJSONObject(i);
                                String b64_image = obj.getString("image");

                                //because b64 is too big to send, we might have to do checking based on the date.
                                String date = obj.getString("date");
                                String comment = obj.getString("comment");

                                // Log the data to verify its working
                                Log.d("Image Base64", b64_image);
                                Log.d("date", date);
                                Log.d("comment", comment);
                                if (date.equals(date_to_find)) {
                                    // If the date matches, add to the list
                                    decode(b64_image);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
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
    private Bitmap decode(String b64){
        byte[] decodedString = Base64.decode(b64, Base64.DEFAULT);
        Bitmap decoded = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        ImageView imageView = findViewById(R.id.imageContainer);
        imageView.setImageBitmap(decoded);
        return decoded;
    }


    //onclick for backbutton


}
